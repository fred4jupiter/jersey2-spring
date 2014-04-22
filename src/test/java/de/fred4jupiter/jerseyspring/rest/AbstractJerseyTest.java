package de.fred4jupiter.jerseyspring.rest;

import de.fred4jupiter.jerseyspring.MyApplication;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;
import org.glassfish.jersey.server.spring.SpringComponentProvider;
import org.glassfish.jersey.test.JerseyTest;

import javax.ws.rs.core.Application;


public abstract class AbstractJerseyTest extends JerseyTest {

    @Override
    protected Application configure() {
        MyApplication myApplication = new MyApplication();
        myApplication.property("contextConfigLocation", "classpath:/applicationContext.xml");
        myApplication.register(SpringComponentProvider.class);
        return myApplication;
    }

    @Override
    protected void configureClient(ClientConfig config) {
        super.configureClient(config);
        config.register(MoxyJsonFeature.class);
    }
}
