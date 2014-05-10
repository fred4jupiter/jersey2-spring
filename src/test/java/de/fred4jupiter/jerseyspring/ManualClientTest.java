package de.fred4jupiter.jerseyspring;

import de.fred4jupiter.jerseyspring.rest.filter.AuthClientRequestFilter;
import de.fred4jupiter.jerseyspring.rest.filter.AuthClientResponseFilter;
import de.fred4jupiter.jerseyspring.service.AuthTokenHolder;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import java.util.UUID;

import static org.junit.Assert.assertNotNull;

public class ManualClientTest {

    private static final Logger LOG = LoggerFactory.getLogger(ManualClientTest.class);

    @Test
    public void callLocalRunningClient() {
        Client client = ClientBuilder.newClient();
        client.register(AuthClientRequestFilter.class);
        //client.register(AuthClientResponseFilter.class);

        AuthTokenHolder.getInstance().setAuthToken(UUID.randomUUID().toString());

        Response response = client.target("http://localhost:8080/greeting/fred").request().get();
        assertNotNull(response);

        String reponseBody = response.readEntity(String.class);
        LOG.debug(reponseBody);
    }
}
