package de.fred4jupiter.jerseyspring.rest;

import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class AlertsResourceTest extends AbstractJerseyTest {

    @Test
    public void listAlerts() {
        Response response = target("alerts").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void listByUser() {
        Response response = target("alerts/{user}").resolveTemplate("user", "fred").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
