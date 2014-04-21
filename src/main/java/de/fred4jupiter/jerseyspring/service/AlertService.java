package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlertService {

    private static final Logger LOG = LoggerFactory.getLogger(AlertService.class);

    private final List<Alert> alerts = new ArrayList<>();

    public List<Alert> findAllAlerts() {
        return alerts;
    }

    @PostConstruct
    public void init() {
        LOG.debug("init: setting up demo data...");
        alerts.add(new Alert("Twitter feeds"));
        alerts.add(new Alert("Spiegel online"));
        alerts.add(new Alert("Facebook news", "fred"));
    }

    public List<Alert> findAlertsOfUser(String user) {
        Assert.notNull(user);
        List<Alert> userAlerts = new ArrayList<>();

        for (Alert alert : alerts) {
            String owner = alert.getOwner();
            if (owner != null && owner.equals(user)) {
                userAlerts.add(alert);
            }
        }

        return userAlerts;
    }

    public void subscribe(String alertId, String user) {
        Assert.notNull(alertId);
        Assert.notNull(user);
        for (Alert alert : alerts) {
            if (alert.getId().equals(alertId)) {
                alert.setOwner(user);
            }
        }
    }

    public Alert getAlertById(String alertId) {
        Assert.notNull(alertId);
        for (Alert alert : alerts) {
            if (alert.getId().equals(alertId)) {
                return alert;
            }
        }
        return null;
    }
}
