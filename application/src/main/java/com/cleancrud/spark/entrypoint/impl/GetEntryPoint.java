package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.EntryPoint;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * GET entrypoint.
 */
@Singleton
public class GetEntryPoint extends EntryPoint {

    @Inject
    public GetEntryPoint() {
        // TODO Usecases to use
    }

    @Override
    protected Response internalHandle(Request request, Response response) {
        Map<String, String> result = new HashMap<>();

        try {
            // TODO here we use the usecases from domain
            result.put("result", "GET RESPONSE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.body(serialize(result));
        return response;
    }
}
