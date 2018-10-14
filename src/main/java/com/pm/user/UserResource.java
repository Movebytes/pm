package com.pm.user;

import com.pm.shared.exception.ExceptionMapper;
import com.pm.shared.exception.BadModelRequestException;
import com.pm.shared.exception.EntityNotFoundException;
import com.pm.user.entity.UserEntity;
import com.pm.user.mapper.UserMapper;
import com.pm.user.model.UserModel;

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
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
    @Context
    private UriInfo uriInfo;

    @EJB
    private UserService userService;

    @Inject
    private UserMapper mapper;

    @Inject
    private ExceptionMapper exceptionMapper;

    @Inject
    private Validator validator;

    /**
     * Find a user by id
     * @param userId
     * @return
     */
    @GET
    @Path("{id}")
    public Response doFind(@PathParam("id") Integer userId) {
        final UserEntity entity = userService.getEntityById(userId);
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        return Response
                .ok(mapper.deepMapToModel(entity))
                .build();
    }

    /**
     * Find all users
     * @return
     */
    @GET
    public Response doFindAll() {
        final List<UserEntity> entities = userService.getAll();
        final List<UserModel> models = new ArrayList<>();
        for (final UserEntity entity : entities) {
            models.add(mapper.deepMapToModel(entity));
        }
        return Response
                .ok(models)
                .build();
    }

    /**
     * Find all active users
     * @return
     */
    @GET
    @Path("/active")
    public Response doFindActive() {
        final List<UserEntity> entities = userService.getActiveUsers();
        final List<UserModel> models = new ArrayList<>();
        for (final UserEntity entity : entities) {
            models.add(mapper.deepMapToModel(entity));
        }
        return Response
                .ok(models)
                .build();
    }

    /**
     * Create a new user
     * @param model
     * @return
     */
    @POST
    public Response doCreate(UserModel model) {
        if (model == null) {
            return exceptionMapper.mapToResponse(new BadModelRequestException());
        }
        final Set<ConstraintViolation<UserModel>> violations = validator.validate(model);
        if (violations.size() != 0) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException(violations.toString()));
        }
        final UserEntity entity = mapper.mapToEntity(model);
        userService.create(entity);
        final URI userUri = uriInfo
                .getAbsolutePathBuilder()
                .path(entity.getId().toString())
                .build();
        return Response
                .created(userUri)
                .build();
    }

    /**
     * Update existing user
     * @param model
     * @return
     */
    @PUT
    public Response doUpdate(UserModel model) {
        if (model == null) {
            return exceptionMapper.mapToResponse(new BadModelRequestException());
        }
        final Set<ConstraintViolation<UserModel>> violations = validator.validate(model);
        if (violations.size() != 0) {
            return exceptionMapper.mapToResponse(new IllegalArgumentException(violations.toString()));
        }
        final UserEntity entity = userService.getEntityById(model.getId());
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        userService.update(mapper.mapToEntity(model));
        return Response
                .ok(model)
                .build();
    }

    /**
     * Delete existing user by id
     * @param userId
     * @return
     */
    @DELETE
    @Path("{id}")
    public Response doDelete(@PathParam("id") Integer userId) {
        final UserEntity entity = userService.getEntityById(userId);
        if (entity == null) {
            return exceptionMapper.mapToResponse(new EntityNotFoundException());
        }
        userService.delete(entity);
        return Response
                .noContent()
                .build();
    }
}