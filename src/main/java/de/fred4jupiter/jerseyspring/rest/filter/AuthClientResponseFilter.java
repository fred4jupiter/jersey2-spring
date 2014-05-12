package de.fred4jupiter.jerseyspring.rest.filter;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

@Provider
public class AuthClientResponseFilter implements ClientResponseFilter {

    private static final Logger LOG = LoggerFactory.getLogger(AuthClientResponseFilter.class);

    @Override
    public void filter(ClientRequestContext requestContext, ClientResponseContext responseContext) throws IOException {
        LOG.debug("filter: response coming back from server. status={}", responseContext.getStatus());

        responseContext.setEntityStream(logResponseBody(responseContext.getEntityStream()));
    }

    private InputStream logResponseBody(InputStream stream) throws IOException {
        if (!stream.markSupported()) {
            stream = new BufferedInputStream(stream);
        }

        try (StringWriter stringWriter = new StringWriter()) {
            IOUtils.copy(stream, stringWriter, "UTF-8");
            LOG.debug("response as String: {}", stringWriter.toString());
        }

        // Reset it so Jersey can read it too.
        stream.reset();
        return stream;
    }
}
