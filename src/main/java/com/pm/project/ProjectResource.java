package com.pm.project;

import com.pm.project.entity.ProjectEntity;
import com.pm.project.mapper.ProjectMapper;
import com.pm.project.model.ProjectModel;
import com.pm.shared.exception.ExceptionMapper;
import com.pm.shared.exception.BadModelRequestException;
import com.pm.shared.exception.EntityNotFoundException;

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

    @Inject
    private ProjectMapper mapper;

    @Inject
    private ExceptionMapper exceptionMapper;

    @Inject
    private Validator validator;

    @GET
    @Path("{id}")
    public Response doProject(@PathParam("id") Integer projectId) {
        final ProjectEntity entity = projectService.getEntityById(projectId);
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        return Response
                .ok(mapper.mapToModel(entity))
                .build();
    }

    @GET
    public Response doAll() {
        final List<ProjectEntity> entities = projectService.getAll();
        final List<ProjectModel> models = new ArrayList<ProjectModel>();
        for (final ProjectEntity entity : entities) {
            models.add(mapper.mapToModel(entity));
        }
        return Response
                .ok(models)
                .build();
    }

    @POST
    public Response doCreate(ProjectModel model) {
        if (model == null) {
            return exceptionMapper.mapToResponse(new BadModelRequestException());
        }
        Set<ConstraintViolation<ProjectModel>> violations = validator.validate(model);
        if (violations.size() != 0) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException(violations.toString()));
        }
        final ProjectEntity entity = mapper.mapToEntity(model);
        projectService.create(entity);
        URI projectUri = uriInfo
                .getAbsolutePathBuilder()
                .path(entity.getId().toString())
                .build();
        return Response
                .created(projectUri)
                .build();
    }

    @PUT
    public Response doUpdate(ProjectModel model) {
        if (model == null) {
            return exceptionMapper.mapToResponse(new BadModelRequestException());
        }
        Set<ConstraintViolation<ProjectModel>> violations = validator.validate(model);
        if (violations.size() != 0) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException(violations.toString()));
        }
        final ProjectEntity entity = projectService.getEntityById(model.getId());
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        projectService.update(mapper.mapToEntity(model));
        return Response
                .ok(model)
                .build();
    }

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
