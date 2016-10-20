package com.bskyb.ovp.graphitemock.samples.mainapp;

import org.junit.BeforeClass;
import org.junit.Test;

import java.net.URL;
import java.util.stream.IntStream;


public class AppProduceMetricsNoMockTest extends AbstractAppTest {


    @BeforeClass
    public static void startServer() throws Exception {
        URL configurationUrl = AppProduceMetricsNoMockTest.class.getClassLoader().getResource("mainApp-test.yml");
        CartoonApp.main("server", configurationUrl.getFile());
    }

    @Test
    public void addCartoon_producesCorrectMetrics() {
        IntStream.rangeClosed(1,1000).forEach(i -> post(new CartoonCard("jp", "sky")));
        //How to verify if metrics were sent to a graphite server?
    }



}
