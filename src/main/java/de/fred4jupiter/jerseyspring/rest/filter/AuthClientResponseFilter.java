package de.fred4jupiter.jerseyspring.rest.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import java.io.IOException;

/**
 * Created by michael on 10.05.2014.
 */
public class AuthClientResponseFilter implements ClientResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthClientResponseFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        LOG.debug("filter: response coming back from server");
    }
}
