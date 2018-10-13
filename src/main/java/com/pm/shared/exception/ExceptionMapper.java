package com.pm.shared.exception;

import org.json.JSONObject;

import javax.enterprise.context.Dependent;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Dependent
public class ExceptionMapper {
    public Response mapToResponse(IllegalArgumentException exception) {
        return Response
                .status(400)
                .entity(new JSONObject().put("message", exception.getMessage()).toString())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response mapToResponse(NotFoundException exception) {
        return Response
                .status(404)
                .entity(new JSONObject().put("message", exception.getMessage()).toString())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }

    public Response mapToResponse(BadRequestException exception) {
        return Response
                .status(406)
                .entity(new JSONObject().put("message", exception.getMessage()).toString())
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}
