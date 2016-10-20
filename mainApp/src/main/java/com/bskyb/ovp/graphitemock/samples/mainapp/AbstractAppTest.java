package com.bskyb.ovp.graphitemock.samples.mainapp;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

public abstract class AbstractAppTest {

    private static final String BASE_URL = "http://localhost:8080";
    private Client client = ClientBuilder.newClient();

    protected void post(CartoonCard cartoonCard) {

        Invocation.Builder target = client.target(BASE_URL).path("/cartoon").
                request(MediaType.APPLICATION_JSON_TYPE);
        target.post(Entity.entity(cartoonCard, MediaType.APPLICATION_JSON_TYPE));

    }
}
