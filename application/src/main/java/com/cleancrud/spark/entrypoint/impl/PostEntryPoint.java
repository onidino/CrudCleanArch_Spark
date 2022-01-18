package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.EntryPoint;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class PostEntryPoint extends EntryPoint {

    @Inject
    public PostEntryPoint() {
        // TODO Usecases to use
    }

    @Override
    protected Object internalHandle(Request request, Response response) {
        Map<String, String> result = new HashMap<>();
        try {
            // TODO here we use the usecases from domain
            result.put("result", "POST RESPONSE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.render(result);
    }
}
