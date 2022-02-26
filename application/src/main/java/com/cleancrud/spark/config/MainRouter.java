package com.cleancrud.spark.config;

import static spark.Spark.after;
import static spark.Spark.delete;
import static spark.Spark.get;
import static spark.Spark.path;
import static spark.Spark.post;
import static spark.Spark.put;

import com.cleancrud.spark.entrypoint.DeleteEntryPoint;
import com.cleancrud.spark.entrypoint.GetEntryPoint;
import com.cleancrud.spark.entrypoint.PostEntryPoint;
import com.cleancrud.spark.entrypoint.PutEntryPoint;
import javax.inject.Singleton;
import spark.RouteGroup;

@Singleton
public class MainRouter implements RouteGroup {

    @Override
    public void addRoutes() {
        after((request, response) -> response.type("application/json"));

        path("/clean/spark", () -> {
            post("/create", Application.getInjectorInstance(PostEntryPoint.class));
            get("/read", Application.getInjectorInstance(GetEntryPoint.class));
            put("/update", Application.getInjectorInstance(PutEntryPoint.class));
            delete("/delete", Application.getInjectorInstance(DeleteEntryPoint.class));
        });
    }
}
