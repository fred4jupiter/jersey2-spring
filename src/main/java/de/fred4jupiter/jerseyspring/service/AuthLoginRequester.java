package de.fred4jupiter.jerseyspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class AuthLoginRequester {

    private static final Logger LOG = LoggerFactory.getLogger(AuthLoginRequester.class);

    //@Scheduled(cron = "0/5 * * * * ?")
    public void fetchLoginToken() {
        LOG.debug("requesting new auth token...");
    }
}
