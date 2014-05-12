package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.service.DemoDataPopulator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AlertsResourceTest extends AbstractJerseyTest {

    @Autowired
    private DemoDataPopulator demoDataPopulator;

    @Test
    public void checkInjectingDependencies() {
        assertNotNull(demoDataPopulator);
    }

    @Test
    public void list() {
        Response response = target().path("alerts").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        Link self = response.getLink("self");
        assertNotNull(self);
        assertThat(self.getUri().toString(), equalTo("http://localhost:9998/alerts"));
    }

    @Test
    public void read() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        Response response = target().path("alerts/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        Link self = response.getLink("self");
        assertNotNull(self);
        assertThat(self.getUri().toString(), equalTo("http://localhost:9998/alerts/"+alert.getId()));
    }

    @Test
    public void create() {
        Alert alert = new Alert("The tales from Bart Simpson", "bart");
        Response response = target().path("alerts").request().post(Entity.entity(alert, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));

        Response responseChecking = target().path("alerts/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        Alert fetchedAlert = responseChecking.readEntity(Alert.class);
        assertThat(fetchedAlert.getTitle(), equalTo(alert.getTitle()));
    }

    @Test
    public void update() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        alert.setTitle("updatedTitle");
        Response response = target().path("alerts").request().put(Entity.entity(alert, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));

        Response responseChecking = target().path("alerts/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        Alert fetchedAlert = responseChecking.readEntity(Alert.class);
        assertThat(fetchedAlert.getTitle(), equalTo(alert.getTitle()));
    }

    @Test
    public void delete() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        Response response = target().path("alerts/{alertId}").resolveTemplate("alertId", alert.getId()).request().delete();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void listAlerts() {
        Response response = target("alerts").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        Link selfLink = response.getLink("self");
        assertNotNull(selfLink);
        assertThat(selfLink.getRel(), equalTo("self"));
        assertThat(selfLink.getUri().toString(), containsString("alerts"));
    }

    @Test
    public void listByUser() {
        Response response = target("alerts/{user}").resolveTemplate("user", "fred").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
