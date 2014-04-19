package com.mkyong.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.mkyong.transaction.TransactionBo;

/**
 * Call this URL
 * <p>
 * <a href="http://localhost:8080/rest/payment/mkyong">http://localhost:8080/rest/payment/mkyong</a>
 * 
 * @author eqpoh
 */
@Component
@Path("/payment")
public class PaymentResource {

  @Autowired
  private TransactionBo transactionBo;

  @GET
  @Path("/mkyong")
  public Response savePayment() {
    String result = transactionBo.save();
    return Response.status(200).entity(result).build();
  }

}