package de.fred4jupiter.jerseyspring.rest;

import org.glassfish.jersey.server.ChunkedOutput;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.io.IOException;

@Path("/chunked")
public class ChunkedOutputResource {

    private int calls = 0;

    @GET
    public ChunkedOutput<String> getChunkedResponse() {
        final ChunkedOutput<String> output = new ChunkedOutput<String>(String.class);

        new Thread() {
            public void run() {
                try {
                    String chunk;

                    while ((chunk = getNextString()) != null) {
                        output.write(chunk);
                    }
                } catch (IOException e) {
                    // IOException thrown when writing the
                    // chunks of response: should be handled
                } finally {
                    try {
                        output.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    // simplified: IOException thrown from
                    // this close() should be handled here...
                }
            }
        }.start();

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
