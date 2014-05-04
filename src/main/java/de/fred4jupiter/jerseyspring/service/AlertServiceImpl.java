package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlertServiceImpl implements AlertService {

    private static final Logger LOG = LoggerFactory.getLogger(AlertServiceImpl.class);

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

    @Override
    public void delete(String alertId) {
        Assert.notNull(alertId);
        LOG.info("deleted alert with id: " + alertId);
        // TODO: implement correctly
    }

    @Override
    public void update(Alert alert) {
        Assert.notNull(alert);
        List<Alert> allAlerts = findAllAlerts();
        for (Alert tmpAlert : allAlerts) {
            if (tmpAlert.getId().equals(alert.getId())) {
                tmpAlert.setTitle(alert.getTitle());
                tmpAlert.setDescription(alert.getDescription());
                tmpAlert.setOwner(alert.getOwner());
            }
        }
    }

    @Override
    public void add(Alert alert) {
        findAllAlerts().add(alert);
    }
}
