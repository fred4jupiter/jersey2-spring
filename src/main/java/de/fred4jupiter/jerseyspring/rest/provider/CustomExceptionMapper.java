package de.fred4jupiter.jerseyspring.rest.provider;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * Exception mapper to convert {@link IllegalArgumentException} into a textual response.
 *
 * @author Marko Asplund (marko.asplund at yahoo.com)
 */
@Provider
public class CustomExceptionMapper implements ExceptionMapper<IllegalArgumentException> {

    @Override
    public Response toResponse(IllegalArgumentException e) {
        return Response.status(Response.Status.BAD_REQUEST).entity("IllegalArgumentException: "+e.getMessage()).build();
    }
}
