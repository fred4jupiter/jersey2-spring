package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.service.DemoDataPopulator;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AlertResourceTest extends AbstractJerseyTest {

    private static final Logger LOG = LoggerFactory.getLogger(AlertResourceTest.class);

    @Autowired
    private DemoDataPopulator demoDataPopulator;

    @Test
    public void checkInjectingDependencies() {
        assertNotNull(demoDataPopulator);
    }

    @Test
    public void list() {
        Response response = target().path("alert/list").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        Link self = response.getLink("self");
        assertNotNull(self);
        assertThat(self.getUri().toString(), equalTo("http://localhost:9998/alert/list"));
    }

    @Test
    public void read() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        Response response = target().path("alert/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
        Link self = response.getLink("self");
        assertNotNull(self);
        assertThat(self.getUri().toString(), equalTo("http://localhost:9998/alert/"+alert.getId()));
    }

    @Test
    public void create() {
        Alert alert = new Alert("The tales from Bart Simpson", "bart");
        Response response = target().path("alert").request().post(Entity.entity(alert, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));

        Response responseChecking = target().path("alert/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        Alert fetchedAlert = responseChecking.readEntity(Alert.class);
        assertThat(fetchedAlert.getTitle(), equalTo(alert.getTitle()));
    }

    @Test
    public void update() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        alert.setTitle("updatedTitle");
        Response response = target().path("alert").request().put(Entity.entity(alert, MediaType.APPLICATION_JSON_TYPE));
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));

        Response responseChecking = target().path("alert/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        Alert fetchedAlert = responseChecking.readEntity(Alert.class);
        assertThat(fetchedAlert.getTitle(), equalTo(alert.getTitle()));
    }

    @Test
    public void delete() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        Response response = target().path("alert/{alertId}").resolveTemplate("alertId", alert.getId()).request().delete();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
