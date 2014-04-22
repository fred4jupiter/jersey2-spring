package de.fred4jupiter.jerseyspring.rest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * NOTE: The application context will be created twice: on server and on client side!
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
public abstract class AbstractSpringJerseyIntegrationTest extends AbstractJerseyTest {

}
