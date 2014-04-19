package de.fred4jupiter.jerseyspring;

import de.fred4jupiter.jerseyspring.provider.CustomExceptionMapper;
import de.fred4jupiter.jerseyspring.provider.JsonMoxyConfigurationContextResolver;
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

        packages(true, "de.fred4jupiter.jerseyspring.rest");

        register(CustomExceptionMapper.class);
        //register(JsonMoxyConfigurationContextResolver.class);
    }
}
