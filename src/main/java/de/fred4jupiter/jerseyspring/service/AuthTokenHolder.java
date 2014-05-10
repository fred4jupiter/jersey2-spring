package de.fred4jupiter.jerseyspring.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthTokenHolder {

    private static final Logger LOG = LoggerFactory.getLogger(AuthTokenHolder.class);

    private static AuthTokenHolder instance = new AuthTokenHolder();

    private String authToken;

    public static AuthTokenHolder getInstance() {
        return instance;
    }

    private AuthTokenHolder() {

    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        LOG.debug("setAuthToken: authToken={}", authToken);
        this.authToken = authToken;
    }
}
