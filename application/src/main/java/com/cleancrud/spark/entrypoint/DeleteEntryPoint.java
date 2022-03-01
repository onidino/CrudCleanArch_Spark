package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.utils.JsonTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.utils.Assert;
import use_cases.DeleteRecordByIdUseCase;

/**
 * DELETE entrypoint.
 */
@Singleton
public class DeleteEntryPoint extends EntryPoint {

  private final DeleteRecordByIdUseCase deleteRecordByIdUseCase;

  @Inject
  public DeleteEntryPoint(
      final DeleteRecordByIdUseCase deleteRecordByIdUseCase, JsonTransformer json) {
    super(json);
    this.deleteRecordByIdUseCase = deleteRecordByIdUseCase;
  }

  @Override
  public Response internalHandle(Request request, Response response) {
    if (!initialValidations(request, response)) {
      return response;
    }
    try {
      Long recordId = deserialize(request.body(), Long.class);
      deleteRecordByIdUseCase.execute(recordId);
    } catch (Exception e) {
      responseException(response, e);
    }
    return response;
  }

  @Override
  public boolean initialValidations(Request request, Response response) {
    try {
      Assert.notNull(request.params("id"), "id cant be null");
    } catch (IllegalArgumentException e) {
      responseException(response, e);
      return false;
    }
    return true;
  }
}
