package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.service.DemoDataPopulator;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.ws.rs.core.Response;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;

public class AlertResourceTest extends AbstractJerseyTest {

    @Autowired
    private DemoDataPopulator demoDataPopulator;

    @Test
    public void checkInjectingDependencies() {
        assertNotNull(demoDataPopulator);
    }

    @Test
    public void listAlerts() {
        Response response = target().path("alerts").request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }

    @Test
    public void getAlertById() {
        Alert alert = demoDataPopulator.getAlerts().get(0);
        Response response = target().path("alert/{alertId}").resolveTemplate("alertId", alert.getId()).request().get();
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));
    }
}
