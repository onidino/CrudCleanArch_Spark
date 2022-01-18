package com.cleancrud.spark.entrypoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.HashMap;
import java.util.Map;

@Singleton
public class GetEntryPoint extends EntryPoint {

    @Inject
    public GetEntryPoint() {
        // TODO Usecases to use
    }

    @Override
    protected Object internalHandle(Request request, Response response) {
        Map<String, String> result = new HashMap<>();
        try {
            // TODO here we use the usecases from domain
            result.put("result", "GET RESPONSE");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return json.render(result);
    }
}
