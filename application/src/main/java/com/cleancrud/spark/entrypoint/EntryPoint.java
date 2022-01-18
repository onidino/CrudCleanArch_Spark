package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.utils.Json;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
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
        String log = String.format("request: /%s", request.uri());
        LOGGER.info(log);
        response.type("application/json");
        response.status(HttpStatus.OK_200);

        return internalHandle(request, response);
    }

    protected abstract Response internalHandle(Request request, Response response);
}
