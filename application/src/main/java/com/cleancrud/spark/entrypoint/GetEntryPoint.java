package com.cleancrud.spark.entrypoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class GetEntryPoint extends EntryPoint {

    private static final Logger LOGGER = LoggerFactory.getLogger(GetEntryPoint.class);

    @Inject
    public GetEntryPoint() {
        // TODO Usecases to use
    }

    @Override
    protected Response internalHandle(Request request, Response response) {
        LOGGER.info("GET");
        try {
            response.body(json.render( "GET RESPONSE"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
