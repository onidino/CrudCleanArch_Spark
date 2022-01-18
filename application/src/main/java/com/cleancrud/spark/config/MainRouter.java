package com.cleancrud.spark.config;

import com.cleancrud.spark.entrypoint.DeleteEntryPoint;
import com.cleancrud.spark.entrypoint.GetEntryPoint;
import com.cleancrud.spark.entrypoint.PostEntryPoint;
import com.cleancrud.spark.entrypoint.PutEntryPoint;
import spark.RouteGroup;

import javax.inject.Singleton;

import static spark.Spark.*;

@Singleton
public class MainRouter implements RouteGroup {

    @Override
    public void addRoutes() {
        path("/clean/spark", () -> {
            post("/create", Application.getInjectorInstance(PostEntryPoint.class));
            get("/read", Application.getInjectorInstance(GetEntryPoint.class));
            put("/update", Application.getInjectorInstance(PutEntryPoint.class));
            delete("/delete", Application.getInjectorInstance(DeleteEntryPoint.class));
        });
    }
}
