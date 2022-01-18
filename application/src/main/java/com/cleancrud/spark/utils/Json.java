package com.cleancrud.spark.utils;

import com.google.gson.Gson;
import spark.ResponseTransformer;

public class Json implements ResponseTransformer {

    private final Gson gson = new Gson();

    @Override
    public String render(Object model) {
        return gson.toJson(model);
    }
}
