package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.rest.filter.AuthClientRequestFilter;
import de.fred4jupiter.jerseyspring.rest.filter.AuthClientResponseFilter;
import org.glassfish.jersey.client.ClientConfig;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class GreetingResourceTest extends AbstractJerseyTest {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingResourceTest.class);

    @Override
    protected void configureClient(ClientConfig config) {
        super.configureClient(config);
        config.register(AuthClientRequestFilter.class);
        config.register(AuthClientResponseFilter.class);
    }

    @Test
    public void greeting() {
        Response response = target("/greeting/{username}").resolveTemplate("username", "fred").request().get();
        assertNotNull(response);
        assertThat(response.getStatus(), equalTo(Response.Status.OK.getStatusCode()));

        MultivaluedMap<String, String> stringHeaders = response.getStringHeaders();
        Set<Map.Entry<String, List<String>>> entries = stringHeaders.entrySet();
        Iterator<Map.Entry<String, List<String>>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, List<String>> next = iterator.next();
            LOG.debug("header string key={}, values={}", next.getKey(), next.getValue());
        }

        Map<String, NewCookie> cookies = response.getCookies();
        Set<Map.Entry<String, NewCookie>> cookiesEntries = cookies.entrySet();
        Iterator<Map.Entry<String, NewCookie>> cookiesIt = cookiesEntries.iterator();
        while(cookiesIt.hasNext()) {
            Map.Entry<String, NewCookie> next = cookiesIt.next();
            LOG.debug("cookie key={}, values={}", next.getKey(), next.getValue());
        }
    }
}
