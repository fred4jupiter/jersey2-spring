package de.fred4jupiter.jerseyspring.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;

@Path("/async")
public class AsyncResource {

    @GET
    public void asyncGet(@Suspended final AsyncResponse asyncResponse) {

//        Executors.newSingleThreadExecutor().submit(new Callable() {
//
//            @Override
//            public Object call() throws Exception {
//                return null;
//            }
//        });
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = veryExpensiveOperation();
                asyncResponse.resume(result);
            }

            private String veryExpensiveOperation() {
                return "Hello".toUpperCase();
            }
        }).start();
    }
}
