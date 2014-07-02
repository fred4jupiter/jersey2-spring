package de.fred4jupiter.jerseyspring.rest;

import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

import de.fred4jupiter.jerseyspring.service.GreetingService;

/**
 * Call this URL for example
 * <p/>
 * <a href="http://localhost:8080/greeting/michael">http://localhost:8080/greeting/michael</a>.
 * <p/>
 * <p>Note: Jersey REST beans are created by Jersey. The bean itself is no spring bean. Only the autowiring takes place.</p>
 *
 * @author eqpoh
 */
@Path("/")
public class GreetingResource {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingResource.class);

    @Autowired
    private GreetingService greetingService;

    @GET
    @Path("/greeting/{username}")
    public Response greeting(@PathParam("username") String username, @CookieParam("authkey") String authkey) {
        LOG.debug("greeting: username={}, authkey={}", username, authkey);
        Assert.notNull(username, "username should not be null");
        Assert.notNull(authkey, "authkey should not be null");
        return Response.ok(greetingService.greeting(username)).build();
    }
}