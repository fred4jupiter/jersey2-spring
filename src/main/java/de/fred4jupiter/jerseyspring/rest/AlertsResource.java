package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.rest.beans.Alerts;
import de.fred4jupiter.jerseyspring.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.List;

@Component
@Path("/alerts")
@Produces(MediaType.APPLICATION_JSON)
@Scope("request")
public class AlertsResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private AlertService alertService;

    @GET
    public Response listAlerts() {
        URI uri = uriInfo.getRequestUriBuilder().build();
        return Response.ok(new Alerts(alertService.findAllAlerts())).link(uri, "self").build();
    }

    @Path("{user}")
    @GET
    public Response listAlertsOfUser(@PathParam("user") String user) {
        Assert.notNull(user);
        List<Alert> alerts = alertService.findAlertsOfUser(user);
        return Response.ok(new Alerts(alerts)).build();
    }
}
