package com.cleancrud.spark.config;

import com.cleancrud.spark.entrypoint.GetEntryPoint;
import spark.RouteGroup;

import javax.inject.Singleton;

import static spark.Spark.get;
import static spark.Spark.path;

@Singleton
public class MainRouter implements RouteGroup {

    @Override
    public void addRoutes() {
        path("/clean/spark", () -> {
            get("/read", Application.getInjectorInstance(GetEntryPoint.class));
            //post("/create", );
            //put("/update", );
            //delete("/remove", );
        });
    }
}
