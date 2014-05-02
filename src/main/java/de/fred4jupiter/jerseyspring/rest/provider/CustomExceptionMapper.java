package de.fred4jupiter.jerseyspring.rest.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class CustomExceptionMapper implements ExceptionMapper<RuntimeException> {

    private static final Logger LOG = LoggerFactory.getLogger(CustomExceptionMapper.class);

    @Override
    public Response toResponse(RuntimeException e) {
        LOG.error(e.getMessage(), e);
        return Response.status(Response.Status.BAD_REQUEST).entity("RuntimeException: " + e.getMessage()).build();
    }
}
