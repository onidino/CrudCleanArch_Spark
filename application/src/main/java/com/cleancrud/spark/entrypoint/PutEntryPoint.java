package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.utils.JsonTransformer;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;

/**
 * PUT entrypoint.
 */
@Singleton
public class PutEntryPoint extends EntryPoint {

  @Inject
  public PutEntryPoint(JsonTransformer json) {
    super(json);
  }

  @Override
  public Response internalHandle(Request request, Response response) {
    Map<String, String> result = new HashMap<>();

    try {
      // TODO here we use the usecases from domain
      result.put("result", "PUT RESPONSE");
      response.body(serialize(result));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return response;
  }
}
