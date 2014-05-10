package de.fred4jupiter.jerseyspring.rest.filter;

import de.fred4jupiter.jerseyspring.service.AuthTokenHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;


public class AuthClientRequestFilter implements ClientRequestFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthClientRequestFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        LOG.debug("filter: request coming in. called URL: {}", requestContext.getUri());

        MultivaluedMap<String, Object> headers = requestContext.getHeaders();

        String authToken = AuthTokenHolder.getInstance().getAuthToken();
        LOG.debug("filter: fetched token={}", authToken);
        headers.add(HttpHeaders.COOKIE, new Cookie("authkey", authToken));
    }
}
