package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.EntryPoint;
import com.cleancrud.spark.utils.JsonTransformer;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;

/**
 * POST entrypoint.
 */
@Singleton
public class PostEntryPoint extends EntryPoint {

  @Inject
  public PostEntryPoint(JsonTransformer json) {
    super(json);
  }

  @Override
  protected Response internalHandle(Request request, Response response) {
    Map<String, String> result = new HashMap<>();

    try {
      // TODO here we use the usecases from domain
      result.put("result", "POST RESPONSE");
      response.body(serialize(result));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return response;
  }
}
