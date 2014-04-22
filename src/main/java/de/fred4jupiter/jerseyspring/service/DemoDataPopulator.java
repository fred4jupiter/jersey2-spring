package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class DemoDataPopulator {

    private static final Logger LOG = LoggerFactory.getLogger(DemoDataPopulator.class);

    private final List<Alert> alerts = new ArrayList<>();

    @PostConstruct
    public void init() {
        LOG.debug("init: setting up demo data...");
        alerts.add(new Alert("Twitter feeds"));
        alerts.add(new Alert("Spiegel online"));
        alerts.add(new Alert("Facebook news", "fred"));
    }

    public List<Alert> getAlerts() {
        return alerts;
    }
}
