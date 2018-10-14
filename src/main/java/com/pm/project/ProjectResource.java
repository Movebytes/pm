package com.pm.project;

import com.pm.project.entity.ProjectEntity;
import com.pm.project.mapper.ProjectMapper;
import com.pm.project.model.ProjectModel;
import com.pm.project.model.ProjectStatus;
import com.pm.shared.exception.ExceptionMapper;
import com.pm.shared.exception.BadModelRequestException;
import com.pm.shared.exception.EntityNotFoundException;
import com.pm.user.UserService;
import com.pm.user.entity.UserEntity;
import com.pm.user.mapper.UserMapper;
import com.pm.user.model.UserModel;
import com.pm.user.model.UserStatus;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Stateless
@Path("/project")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProjectResource {
    @Context
    private UriInfo uriInfo;

    @EJB
    private ProjectService projectService;

    @EJB
    private UserService userService;

    @Inject
    private ProjectMapper projectMapper;

    @Inject
    private UserMapper userMapper;

    @Inject
    private ExceptionMapper exceptionMapper;

    @Inject
    private Validator validator;

    /**
     * Find a project by id
     * @param projectId
     * @return
     */
    @GET
    @Path("{id}")
    public Response doProject(@PathParam("id") Integer projectId) {
        final ProjectEntity entity = projectService.getEntityById(projectId);
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        return Response
                .ok(projectMapper.deepMapToModel(entity))
                .build();
    }

    /**
     * Find all projects
     * @return
     */
    @GET
    public Response doAll() {
        final List<ProjectEntity> entities = projectService.getAll();
        final List<ProjectModel> models = new ArrayList<ProjectModel>();
        for (final ProjectEntity entity : entities) {
            models.add(projectMapper.deepMapToModel(entity));
        }
        return Response
                .ok(models)
                .build();
    }

    /**
     * Find all active projects
     * @return
     */
    @GET
    @Path("/active")
    public Response doFindActive() {
        final List<ProjectEntity> entities = projectService.getActiveProjects();
        final List<ProjectModel> models = new ArrayList<>();
        for (final ProjectEntity entity : entities) {
            models.add(projectMapper.deepMapToModel(entity));
        }
        return Response
                .ok(models)
                .build();
    }

    /**
     * Create a new project
     * @param model
     * @return
     */
    @POST
    public Response doCreate(ProjectModel model) {
        if (model == null) {
            return exceptionMapper.mapToResponse(new BadModelRequestException());
        }
        final Set<ConstraintViolation<ProjectModel>> violations = validator.validate(model);
        if (violations.size() != 0) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException(violations.toString()));
        }
        final ProjectEntity entity = projectMapper.mapToEntity(model);
        projectService.create(entity);
        URI projectUri = uriInfo
                .getAbsolutePathBuilder()
                .path(entity.getId().toString())
                .build();
        return Response
                .created(projectUri)
                .build();
    }

    /**
     * Update existing project
     * @param model
     * @return
     */
    @PUT
    public Response doUpdate(ProjectModel model) {
        if (model == null) {
            return exceptionMapper.mapToResponse(new BadModelRequestException());
        }
        final Set<ConstraintViolation<ProjectModel>> violations = validator.validate(model);
        if (violations.size() != 0) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException(violations.toString()));
        }
        final ProjectEntity entity = projectService.getEntityById(model.getId());
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        projectService.update(projectMapper.mapToEntity(model));
        return Response
                .ok(model)
                .build();
    }

    @PUT
    @Path("{id}/assign/{userId}")
    public Response doAssign(@PathParam("id") Integer id, @PathParam("userId") Integer userId) {
        final ProjectEntity projectEntity = projectService.getEntityById(id);
        if (projectEntity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        if (projectEntity.getStatus() != ProjectStatus.ACTIVE) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException("Project must be active"));
        }
        final UserEntity userEntity = userService.getEntityById(userId);
        if (userEntity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        final UserModel userModel = userMapper.deepMapToModel(userEntity);
        if (userModel.getStatus() != UserStatus.ACTIVE) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException("User must be active"));
        }
        if (userModel.getProjects().size() != 0) {
            return exceptionMapper.mapToResponse(new WebApplicationException("User already assigned to active project"));
        }
        projectEntity.getUsers().add(userEntity);
        userEntity.getProjects().add(projectEntity);
        return Response
                .ok(projectMapper.deepMapToModel(projectService.update(projectEntity)))
                .build();
    }

    /**
     * Delete existing project by id
     * @param projectId
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response doDelete(@PathParam("id") Integer projectId) {
        final ProjectEntity entity = projectService.getEntityById(projectId);
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        projectService.delete(entity);
        return Response
                .noContent()
                .build();
    }
}
