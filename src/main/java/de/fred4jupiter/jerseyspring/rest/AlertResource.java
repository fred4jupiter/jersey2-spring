package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import de.fred4jupiter.jerseyspring.rest.beans.Alerts;
import de.fred4jupiter.jerseyspring.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;

@Component
@Path("/alert")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Scope("request")
public class AlertResource {

    @Context
    private UriInfo uriInfo;

    @Autowired
    private AlertService alertService;

    @GET
    @Path("list")
    public Response list() {
        return Response.ok(new Alerts(alertService.findAllAlerts())).links(createSelfLink()).build();
    }

    private Link createSelfLink() {
        URI uri = uriInfo.getRequestUriBuilder().build();
        return Link.fromUri(uri).rel("self").build();
    }

    @GET
    @Path("{id}")
    public Response read(@PathParam("id") String alertId) {
        Assert.notNull(alertId);
        Alert alert = alertService.getAlertById(alertId);
        return Response.ok(alert).links(createSelfLink()).build();
    }

    @POST
    public Response create(Alert alert) {
        alertService.add(alert);
        return Response.ok(alert).build();
    }

    @PUT
    public Response update(Alert alert) {
        alertService.update(alert);
        return Response.ok(alert).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("id") String alertId) {
        alertService.delete(alertId);
        return Response.ok(alertId).build();
    }

}
