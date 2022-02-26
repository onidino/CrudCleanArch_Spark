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
 * DELETE entrypoint.
 */
@Singleton
public class DeleteEntryPoint extends EntryPoint {

  @Inject
  public DeleteEntryPoint(JsonTransformer json) {
    super(json);
  }

  @Override
  protected Response internalHandle(Request request, Response response) {
    Map<String, String> result = new HashMap<>();

    try {
      // TODO here we use the usecases from domain
      result.put("result", "DELETE RESPONSE");
      response.body(serialize(result));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return response;
  }
}
