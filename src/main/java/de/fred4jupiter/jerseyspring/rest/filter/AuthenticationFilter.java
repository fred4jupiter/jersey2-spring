package de.fred4jupiter.jerseyspring.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;

@PreMatching
public class AuthenticationFilter implements ContainerRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationFilter.class);

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        LOG.debug("filter: request coming in...");
        //requestContext.getCookies().put(HttpHeaders.COOKIE, new Cookie("authkey", "abc"));
    }
}
