package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService{

    private final DemoDataPopulator demoDataPopulator;

    @Autowired
    public AlertServiceImpl(DemoDataPopulator demoDataPopulator) {
        this.demoDataPopulator = demoDataPopulator;
    }

    @Override
    public List<Alert> findAllAlerts() {
        return demoDataPopulator.getAlerts();
    }

    @Override
    public List<Alert> findAlertsOfUser(String user) {
        Assert.notNull(user);
        List<Alert> userAlerts = new ArrayList<>();

        for (Alert alert : findAllAlerts()) {
            String owner = alert.getOwner();
            if (owner != null && owner.equals(user)) {
                userAlerts.add(alert);
            }
        }

        return userAlerts;
    }

    @Override
    public Alert getAlertById(String alertId) {
        Assert.notNull(alertId);
        for (Alert alert : findAllAlerts()) {
            if (alert.getId().equals(alertId)) {
                return alert;
            }
        }
        return null;
    }
}
