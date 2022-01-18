package com.cleancrud.spark.config;

import spark.servlet.SparkApplication;

public class Application implements SparkApplication {

    private final MainRouter mainRouter;

    public Application() {
        this.mainRouter = new MainRouter();
    }

    @Override
    public void init() {
        // endpoints
        mainRouter.addRoutes();
    }

    public static void main(String[] args){
        new Application().init();
    }
}
