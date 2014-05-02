package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/applicationContext.xml")
public class AlertServiceTest {

    @Autowired
    private AlertService alertService;

    @Test
    public void findAllAlerts() {
        List<Alert> allAlerts = alertService.findAllAlerts();
        assertNotNull(allAlerts);
    }

    @Test
    public void findAlertsOfUser() {
        List<Alert> alertsOfUserFred = alertService.findAlertsOfUser("fred");
        assertNotNull(alertsOfUserFred);
    }

}
