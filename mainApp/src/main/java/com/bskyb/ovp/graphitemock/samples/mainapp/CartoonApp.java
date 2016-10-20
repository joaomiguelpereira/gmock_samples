package com.bskyb.ovp.graphitemock.samples.mainapp;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class CartoonApp extends Application<AppConfiguration> {

    private static CartoonApp app;
    public static void main(String...args) throws Exception {
        app = new CartoonApp();
        app.run(args);
    }

    public void run(AppConfiguration mainAppConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new CartoonCardResource());
    }


}
