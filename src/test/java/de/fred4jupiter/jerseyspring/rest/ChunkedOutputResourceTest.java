package de.fred4jupiter.jerseyspring.rest;

import org.glassfish.jersey.client.ChunkedInput;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import static org.junit.Assert.*;

public class ChunkedOutputResourceTest extends AbstractJerseyTest {

    private static final Logger LOG = LoggerFactory.getLogger(ChunkedOutputResourceTest.class);

    @Test
    public void requestChunks() {
        Response response = target("chunked").request().get();
        final ChunkedInput<String> chunkedInput = response.readEntity(new GenericType<ChunkedInput<String>>() {
        });

        assertNotNull(chunkedInput);

        String chunkedResponseString = chunkedInput.read();
        assertNotNull(chunkedResponseString);
        LOG.debug("chunkedResponseString=" + chunkedResponseString);
    }
}
