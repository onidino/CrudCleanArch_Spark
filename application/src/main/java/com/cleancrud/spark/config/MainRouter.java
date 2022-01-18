package com.cleancrud.spark.config;

import com.cleancrud.spark.entrypoint.GetEntryPoint;
import spark.RouteGroup;

import javax.inject.Inject;
import javax.inject.Singleton;

import static spark.Spark.get;
import static spark.Spark.path;

@Singleton
public class MainRouter implements RouteGroup {

    private final GetEntryPoint getEntryPoint;

    @Inject
    public MainRouter() {
        this.getEntryPoint = new GetEntryPoint();
    }

    @Override
    public void addRoutes() {
        path("/clean", () -> {
            path("/spark", () -> {
                get("/read", getEntryPoint);
                //post("/create", );
                //put("/update", );
                //delete("/remove", );
            });
        });
    }
}
