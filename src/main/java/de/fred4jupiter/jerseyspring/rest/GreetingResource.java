package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.ResourceContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Call this URL for example
 * <p/>
 * <a href="http://localhost:8080/greeting/michael">http://localhost:8080/greeting/michael</a>
 *
 * @author eqpoh
 */
@Component
@Path("/")
@Scope("request")
public class GreetingResource {

    @Context
    private ResourceContext resourceContext;

    @Context
    private UriInfo uriInfo;

    @Autowired
    private GreetingService greetingService;

    @GET
    @Path("/greeting/{username}")
    public Response savePayment(@PathParam("username") String username) {
        return Response.status(200).entity(greetingService.greeting(username)).build();
    }
}