package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.EntryPoint;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * PUT entrypoint.
 */
@Singleton
public class PutEntryPoint extends EntryPoint {

    @Inject
    public PutEntryPoint() {
        // TODO Usecases to use
    }

    @Override
    protected Response internalHandle(Request request, Response response) {
        Map<String, String> result = new HashMap<>();

        try {
            // TODO here we use the usecases from domain
            result.put("result", "PUT RESPONSE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.body(serialize(result));
        return response;
    }
}
