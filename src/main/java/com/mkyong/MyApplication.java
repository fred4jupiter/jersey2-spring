package com.mkyong;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.mkyong.rest.PaymentResource;

/**
 * @author eqpoh
 */
public class MyApplication extends ResourceConfig {

  /**
   * Register JAX-RS application components.
   */
  public MyApplication() {
    register(RequestContextFilter.class);

    register(PaymentResource.class);
  }

}
