package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import com.cleancrud.spark.entrypoint.mapper.RecordMapper;
import com.cleancrud.spark.utils.JsonTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.utils.Assert;
import use_cases.GetRecordByIdUseCase;

/**
 * GET entrypoint.
 */
@Singleton
public class GetEntryPoint extends EntryPoint {

  private final GetRecordByIdUseCase getRecordByIdUseCase;

  @Inject
  public GetEntryPoint(final GetRecordByIdUseCase getRecordByIdUseCase, JsonTransformer json) {
    super(json);
    this.getRecordByIdUseCase = getRecordByIdUseCase;
  }

  @Override
  public Response internalHandle(Request request, Response response) {
    if (!entryValidations(request, response)) {
      return response;
    }
    try {
      Long recordId = Long.valueOf(request.params("id"));
      RecordDto result = RecordMapper.entityToDto(getRecordByIdUseCase.execute(recordId));
      response.body(serialize(result));
    } catch (Exception e) {
      responseException(response, e);
    }

    return response;
  }

  @Override
  public boolean entryValidations(Request request, Response response) {
    try {
      Assert.notNull(request.params("id"), "id cant be null");
    } catch (IllegalArgumentException e) {
      responseException(response, e);
      return false;
    }
    return true;
  }
}
