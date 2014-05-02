package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.service.AlertService;
import de.fred4jupiter.jerseyspring.service.AlertServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Component
@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Scope("request")
public class AlertResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private AlertService alertService;

    @Path("alerts")
    @GET
    public List<Alert> listAlerts() {
        return alertService.findAllAlerts();
    }

    @Path("alerts/{user}")
    @GET
    public List<Alert> listAlertsOfUser(@PathParam("user") String user) {
        Assert.notNull(user);
        return alertService.findAlertsOfUser(user);
    }

    @Path("alert/{alertId}")
    @GET
    public Response getAlertById(@PathParam("alertId") String alertId) {
        Assert.notNull(alertId);
        Alert alert = alertService.getAlertById(alertId);
        UriBuilder uriBuilder = uriInfo.getAbsolutePathBuilder();
        URI uri = uriBuilder.path(alertId).build();

        return Response.ok(alert).link(uri, "self").build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Alert updateAlert(Alert alert) {
        return alert;
    }
}
