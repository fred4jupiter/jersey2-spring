package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Component
@Path("alerts")
public class AlertResource {

    @Autowired
    private AlertService alertService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Alert> listAlerts() {
        return alertService.findAllAlerts();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Alert roundTrip(Alert alert) {
        return alert;
    }
}
