package com.example.wildflymicroprofile.rest;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class IllegalStateExceptionMapper implements ExceptionMapper<IllegalStateException>{

    @Override
    public Response toResponse(IllegalStateException exception) {
        return Response
                .status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(exception.getMessage())
                .type(MediaType.TEXT_PLAIN_TYPE)
                .build();
    }

}
