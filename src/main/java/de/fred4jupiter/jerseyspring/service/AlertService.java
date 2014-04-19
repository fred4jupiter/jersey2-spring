package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private static final Logger LOG = LoggerFactory.getLogger(AlertService.class);

    private List<Alert> alerts = new ArrayList<>();

    public List<Alert> findAllAlerts() {
        return alerts;
    }

    @PostConstruct
    public void init() {
        LOG.debug("init: setting up demo data...");
        alerts.add(new Alert("Twitter feeds"));
        alerts.add(new Alert("Spiegel online"));
    }
}
