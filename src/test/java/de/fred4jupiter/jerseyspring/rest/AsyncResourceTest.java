package de.fred4jupiter.jerseyspring.rest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class AsyncResourceTest extends AbstractJerseyTest {

    private static final Logger LOG = LoggerFactory.getLogger(AsyncResourceTest.class);

    @Test
    public void callAsyncMethod() throws ExecutionException, InterruptedException {
        Future<Response> asyncResponse = target("async").request().async().get();
        assertNotNull(asyncResponse);
        Response response = asyncResponse.get();
        assertNotNull(response);
        String bodyResponse = response.readEntity(String.class);
        LOG.debug("bodyResponse=" + bodyResponse);
        assertThat(bodyResponse, equalTo("HELLO"));
    }
}
