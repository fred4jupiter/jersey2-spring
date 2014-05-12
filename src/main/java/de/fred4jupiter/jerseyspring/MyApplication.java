package de.fred4jupiter.jerseyspring;

import de.fred4jupiter.jerseyspring.rest.AlertsResource;
import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

/**
 * @author eqpoh
 */
public class MyApplication extends ResourceConfig {

    /**
     * Note: You only have to register resources, providers that are not located in specified packages.
     */
    public MyApplication() {
        register(RequestContextFilter.class);

        packages(true, AlertsResource.class.getPackage().getName());

        // Providers - JSON.
        register(MoxyJsonFeature.class);
        register(LoggingFilter.class);

//        property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
//        property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
//        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

//        property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
//        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    }
}
