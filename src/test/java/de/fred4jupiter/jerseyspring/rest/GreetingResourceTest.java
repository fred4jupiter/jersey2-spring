package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.filter.AuthClientRequestFilter;
import de.fred4jupiter.jerseyspring.rest.filter.AuthClientResponseFilter;
import de.fred4jupiter.jerseyspring.rest.filter.AuthenticationFilter;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.filter.LoggingFilter;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;


public class GreetingResourceTest extends AbstractJerseyTest {

    @Override
    protected void configureClient(ClientConfig config) {
        super.configureClient(config);
        config.register(AuthClientRequestFilter.class);
        config.register(AuthClientResponseFilter.class);
    }

    @Test
    public void greeting() {
        Response response = target("/greeting/{username}").resolveTemplate("username", "fred").request().get();
        assertNotNull(response);
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
