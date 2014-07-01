package de.fred4jupiter.jerseyspring.rest;

import org.glassfish.jersey.server.ChunkedOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;

@Path("/chunked")
public class ChunkedOutputResource {

    private static final Logger LOG = LoggerFactory.getLogger(ChunkedOutputResource.class);

    @Autowired
    private TaskExecutor taskExecutor;

    private int calls = 0;

    @GET
    public ChunkedOutput<String> getChunkedResponse() {
        final ChunkedOutput<String> output = new ChunkedOutput<String>(String.class);

        taskExecutor.execute(new Runnable() {

            @Override
            public void run() {
                try {
                    String chunk;
                    while ((chunk = getNextString()) != null) {
                        output.write(chunk);
                    }
                } catch (IOException e) {
                    LOG.error(e.getMessage(), e);
                    // IOException thrown when writing the
                    // chunks of response: should be handled
                } finally {
                    try {
                        output.close();
                    } catch (IOException e) {
                        LOG.error(e.getMessage(), e);
                    }
                    // simplified: IOException thrown from
                    // this close() should be handled here...
                }
            }
        });

        // the output will be probably returned even before
        // a first chunk is written by the new thread
        return output;
    }

    private String getNextString() {
        if (calls == 5) {
            return null;
        }

        calls++;
        return String.valueOf(System.currentTimeMillis());
    }
}
