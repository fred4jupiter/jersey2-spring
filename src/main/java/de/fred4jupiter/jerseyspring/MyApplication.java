package de.fred4jupiter.jerseyspring;

import de.fred4jupiter.jerseyspring.provider.CustomExceptionMapper;
import de.fred4jupiter.jerseyspring.rest.AlertResource;
import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.CommonProperties;
import org.glassfish.jersey.moxy.internal.MoxyFilteringFeature;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.moxy.xml.MoxyXmlFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import javax.ws.rs.ext.ContextResolver;

/**
 * @author eqpoh
 */
public class MyApplication extends ResourceConfig {

    /**
     * Register JAX-RS application components.
     */
    public MyApplication() {
        register(RequestContextFilter.class);

        packages(true, AlertResource.class.getPackage().getName());

        // Providers - JSON.
        register(MoxyJsonFeature.class);


//        register(MoxyXmlFeature.class);
//        register(MoxyFilteringFeature.class);
//        register(MOXyJsonProvider.class);

        register(CustomExceptionMapper.class);
        //register(JsonMoxyConfigurationContextResolver.class);

//        property(CommonProperties.MOXY_JSON_FEATURE_DISABLE, true);
//        property(CommonProperties.JSON_PROCESSING_FEATURE_DISABLE, true);
//        property(CommonProperties.FEATURE_AUTO_DISCOVERY_DISABLE, true);

//        property(CommonProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
//        property(ServerProperties.METAINF_SERVICES_LOOKUP_DISABLE, true);
    }

    /**
     * Configuration for {@link org.eclipse.persistence.jaxb.rs.MOXyJsonProvider} - outputs formatted JSON.
     */
    public static class JsonConfiguration implements ContextResolver<MoxyJsonConfig> {

        @Override
        public MoxyJsonConfig getContext(final Class<?> type) {
            final MoxyJsonConfig config = new MoxyJsonConfig();
            config.setFormattedOutput(true);
            return config;
        }
    }
}
