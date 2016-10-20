package com.bskyb.ovp.graphitemock.samples.mainapp;

import com.codahale.metrics.annotation.Timed;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


@Path("/cartoon")
@Produces(MediaType.APPLICATION_JSON)
@Slf4j
public class CartoonCardResource {

    @Timed(name = "add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addEntity(CartoonCard entity) {
        log.info("Adding new CartoonCard " + entity);
        doSomeHeavyOp();
        return Response.ok(entity).build();
    }

    private void doSomeHeavyOp() {
        try {
            Thread.sleep(random());
        } catch (InterruptedException e) {
            //Ignored
        }
    }

    private long random() {
        return ThreadLocalRandom.current().nextInt(10, 50);
    }
}
