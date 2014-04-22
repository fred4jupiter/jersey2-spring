package de.fred4jupiter.jerseyspring.rest;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class GreetingResourceTest extends AbstractJerseyTest {

    @Test
    public void greeting() {
        Response response = target("/greeting/{username}").resolveTemplate("username", "fred").request().get();
        assertNotNull(response);
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
