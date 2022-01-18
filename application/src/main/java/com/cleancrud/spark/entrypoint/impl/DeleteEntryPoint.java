package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.EntryPoint;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

/**
 * DELETE entrypoint.
 */
@Singleton
public class DeleteEntryPoint extends EntryPoint {

    @Inject
    public DeleteEntryPoint() {
        // TODO Usecases to use
    }

    @Override
    protected Response internalHandle(Request request, Response response) {
        Map<String, String> result = new HashMap<>();

        try {
            // TODO here we use the usecases from domain
            result.put("result", "DELETE RESPONSE");
        } catch (Exception e) {
            e.printStackTrace();
        }

        response.body(serialize(result));
        return response;
    }
}
