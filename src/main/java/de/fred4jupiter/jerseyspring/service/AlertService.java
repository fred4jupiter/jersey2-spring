package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;

import java.util.List;


public interface AlertService {

    List<Alert> findAllAlerts();

    List<Alert> findAlertsOfUser(String user);

    Alert getAlertById(String alertId);

    void delete(String alertId);

    void update(Alert alert);

    void add(Alert alert);
}
