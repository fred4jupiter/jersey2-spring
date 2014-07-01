package de.fred4jupiter.jerseyspring.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;

@Path("/async")
public class AsyncResource {

    @Autowired
    private TaskExecutor taskExecutor;

    @GET
    public void asyncGet(@Suspended final AsyncResponse asyncResponse) {
        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                String result = veryExpensiveOperation();
                asyncResponse.resume(result);
            }
        });
    }

    private String veryExpensiveOperation() {
        return "Hello".toUpperCase();
    }
}
