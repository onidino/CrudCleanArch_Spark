package com.cleancrud.spark.utils;

import static com.google.gson.FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import spark.ResponseTransformer;

public class JsonTransformer implements ResponseTransformer {

  private final Gson gson = new GsonBuilder()
      .setFieldNamingPolicy(LOWER_CASE_WITH_UNDERSCORES)
      .create();

  @Override
  public String render(Object model) {
    return gson.toJson(model);
  }

  public <T> T toObject(String jsonString, Class<T> clazz) {
    return gson.fromJson(jsonString, clazz);
  }
}

