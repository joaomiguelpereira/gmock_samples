package com.bskyb.ovp.graphitemock.samples.mainapp;

import com.bskyb.internettv.graphitemock.api.GraphiteServer;
import com.bskyb.internettv.graphitemock.api.MetricRecord;
import com.bskyb.internettv.graphitemock.local.LocalGraphiteServer;
import org.assertj.core.api.Assertions;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import scala.collection.immutable.List;

import java.net.URL;
import java.util.stream.IntStream;

public class AppProducesMetricsMockServer extends AbstractAppTest {

    private static GraphiteServer mockGraphiteServer = new LocalGraphiteServer(2003);

    @BeforeClass
    public static void startServer() throws Exception {
        mockGraphiteServer.start();
        URL configurationUrl = AppProduceMetricsNoMockTest.class.getClassLoader().getResource("mainApp-test.yml");
        CartoonApp.main("server", configurationUrl.getFile());

    }
    @AfterClass
    public static void stopMockServer() {
        mockGraphiteServer.stop();
    }

    @Before
    public void resetGraphiteMock() {
        mockGraphiteServer.clear();
    }

    @Test
    public void addCartoon_producesCorrectMetrics() {

        //Given
        Assertions.assertThat(mockGraphiteServer.metrics().length()).isEqualTo(0);

        IntStream.rangeClosed(1,100).forEach(i -> post(new CartoonCard("jp", "sky")));
        //How to verify if metrics were sent to a graphite server?
        List<MetricRecord> metrics = mockGraphiteServer.metrics();
        Assertions.assertThat(metrics.length()).isGreaterThan(0);
    }


}
