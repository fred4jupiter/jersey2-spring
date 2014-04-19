package de.fred4jupiter.jerseyspring;

import de.fred4jupiter.jerseyspring.provider.CustomExceptionMapper;
import de.fred4jupiter.jerseyspring.rest.GreetingResource;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author eqpoh
 */
public class MyApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication() {
        register(RequestContextFilter.class);

        register(GreetingResource.class);

        register(CustomExceptionMapper.class);
    }
}
