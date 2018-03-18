package com.example.wildflymicroprofile.rest;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException>{

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity("No name provided")
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
