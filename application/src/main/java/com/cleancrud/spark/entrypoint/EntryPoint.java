package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.entrypoint.dto.ResponseException;
import com.cleancrud.spark.utils.JsonTransformer;
import javax.inject.Inject;
import org.eclipse.jetty.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * Main abstract class for entrypoints to inherit
 */
public abstract class EntryPoint implements Route {

  private static final Logger LOGGER = LoggerFactory.getLogger(EntryPoint.class);
  protected final JsonTransformer json;

  @Inject
  protected EntryPoint(JsonTransformer json) {
    if (json == null) {
      json = new JsonTransformer();
    }
    this.json = json;
  }

  @Override
  public Response handle(Request request, Response response) {
    final String logMsg = String.format("request: %s - %s", request.requestMethod(), request.uri());
    LOGGER.info(logMsg);

    response.type("application/json");
    response.status(HttpStatus.OK_200);

    return internalHandle(request, response);
  }

  /**
   * The handle of the entrypoints
   *
   * @param request  the request
   * @param response the response
   * @return the response
   */
  public abstract Response internalHandle(Request request, Response response);

  /**
   * JsonTransformer to transform object to json string
   *
   * @param object the object to serialize
   * @return the json string
   */
  protected String serialize(Object object) {
    return json.render(object);
  }


  /**
   * JsonTransformer to transform json string to object of class<T>
   *
   * @param jsonString the json string
   * @param clazz      the class to transform
   * @param <T>        the class of the object
   * @return the object transformed
   */
  protected <T> T deserialize(String jsonString, Class<T> clazz) {
    return json.toObject(jsonString, clazz);
  }

  protected Response responseException(Response response, Exception ex) {
    response.body(serialize(
        new ResponseException(
            ex.getClass().toString(),
            ex.getMessage())
    ));
    return response;
  }
}
