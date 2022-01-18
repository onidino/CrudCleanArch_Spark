package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.utils.Json;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

public abstract class EntryPoint implements Route {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntryPoint.class);
    protected Json json;

    protected EntryPoint() {
        this.json = new Json();
    }

    @Override
    public Object handle(Request request, Response response) {
        LOGGER.info("request: {} - {}", request.requestMethod(), request.uri());

        response.type("application/json");
        response.status(HttpStatus.OK_200);
        return internalHandle(request, response);
    }

    protected abstract Object internalHandle(Request request, Response response);
}
