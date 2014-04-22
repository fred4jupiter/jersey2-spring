package de.fred4jupiter.jerseyspring.rest.provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class LoggingRequestFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(LoggingRequestFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOG.debug("called URI: {}", requestContext.getUriInfo().getAbsolutePath());
    }
}
