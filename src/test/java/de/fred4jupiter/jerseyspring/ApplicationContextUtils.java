package de.fred4jupiter.jerseyspring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Helper class for accessing the applicationContext from within jersey tests.
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null) {
            String msg = "The applicationContext is not yet available. " +
                    "Please ensure that the spring applicationContext is completly created before calling this method!";
            throw new IllegalStateException(msg);
        }

        return applicationContext;
    }
}
