package com.bskyb.ovp.graphitemock.samples.integration;

import com.bskyb.internettv.graphitemock.api.GraphiteServer;
import com.bskyb.internettv.graphitemock.remote.RemoteGraphiteServer;
import com.bskyb.ovp.graphitemock.samples.mainapp.CartoonCard;
import org.assertj.core.api.Assertions;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static com.jayway.awaitility.Awaitility.await;

public class MainAppIntegrationTest {


    private static final String remoteGraphiteMockServerUrl = "http://localhost:8088";
    private GraphiteServer remoteGraphiteServer = new RemoteGraphiteServer(remoteGraphiteMockServerUrl);

    @Test
    public void shouldProduceMetrics() {

        remoteGraphiteServer.clear();
        Assertions.assertThat(remoteGraphiteServer.metrics().length()).isZero();

        Client client = ClientBuilder.newClient();

        Invocation.Builder target = client.target("http://localhost:8080").path("/cartoon").
                request(MediaType.APPLICATION_JSON_TYPE);

        IntStream.rangeClosed(0, 100).forEach(i -> target.post(Entity.entity(new CartoonCard("jp", "sky"), MediaType.APPLICATION_JSON_TYPE)));

        await().atMost(30, TimeUnit.SECONDS).until(() ->
                Assertions.assertThat(remoteGraphiteServer.metrics().length()).isGreaterThan(0));

    }
}
