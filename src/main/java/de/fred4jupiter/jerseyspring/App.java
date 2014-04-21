package de.fred4jupiter.jerseyspring;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.ext.ContextResolver;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class App {

    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    private static final URI BASE_URI = URI.create("http://localhost:9998/jsonmoxy/");

    @SuppressWarnings({"ResultOfMethodCallIgnored"})
    public static void main(String[] args) {
        try {
            System.out.println("JSON with MOXy Jersey Example App");

            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(BASE_URI, createApp());

            System.out.println(String.format("Application started.%nHit enter to stop it..."));
            System.in.read();
            server.shutdownNow();
        } catch (IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public static ResourceConfig createApp() {
        return new ResourceConfig().
                packages("de.fred4jupiter.jerseyspring.rest").
                register(createMoxyJsonResolver());
    }

    public static ContextResolver<MoxyJsonConfig> createMoxyJsonResolver() {
        final MoxyJsonConfig moxyJsonConfig = new MoxyJsonConfig();
        Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
        namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");
        moxyJsonConfig.setNamespacePrefixMapper(namespacePrefixMapper).setNamespaceSeparator(':');
        return moxyJsonConfig.resolver();
    }
}
