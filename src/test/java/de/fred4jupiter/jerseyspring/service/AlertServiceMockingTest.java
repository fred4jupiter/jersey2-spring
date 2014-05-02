package de.fred4jupiter.jerseyspring.service;

import de.fred4jupiter.jerseyspring.rest.beans.Alert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoContextLoader;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoContextLoader.class, locations = "classpath:/applicationContext.xml")
public class AlertServiceMockingTest {

    @Autowired
    private AlertService alertService;

    @ReplaceWithMock
    @Autowired
    private DemoDataPopulator demoDataPopulator;

    @Mock
    private Alert alert;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void findAllAlerts() {
        when(demoDataPopulator.getAlerts()).thenReturn(Arrays.asList(alert));

        List<Alert> allAlerts = alertService.findAllAlerts();
        assertNotNull(allAlerts);
        assertThat(allAlerts.size(), equalTo(1));
        assertThat(allAlerts.get(0), equalTo(alert));
    }

}
