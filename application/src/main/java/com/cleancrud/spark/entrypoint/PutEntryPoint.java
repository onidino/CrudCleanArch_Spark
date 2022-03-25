package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import com.cleancrud.spark.entrypoint.mapper.RecordMapper;
import com.cleancrud.spark.utils.JsonTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.utils.Assert;
import use_cases.UpdateRecordByIdUseCase;

/**
 * PUT entrypoint.
 */
@Singleton
public class PutEntryPoint extends EntryPoint {

  private final UpdateRecordByIdUseCase updateRecordByIdUseCase;

  @Inject
  public PutEntryPoint(final UpdateRecordByIdUseCase updateRecordByIdUseCase,
      final JsonTransformer json) {
    super(json);
    this.updateRecordByIdUseCase = updateRecordByIdUseCase;
  }

  @Override
  public Response internalHandle(Request request, Response response) {
    if (!entryValidations(request, response)) {
      return response;
    }
    try {
      Long recordId = Long.valueOf(request.params("id"));
      RecordDto recordDto = deserialize(request.body(), RecordDto.class);
      RecordDto result = RecordMapper.entityToDto(
          updateRecordByIdUseCase.execute(recordId, recordDto.getData()));
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
      Assert.notNull(request.body(), "body cant be empty");
      RecordDto recordDto = deserialize(request.body(), RecordDto.class);
      Assert.notNull(recordDto.getData(), "field data in body cant be null");
    } catch (IllegalArgumentException e) {
      responseException(response, e);
      return false;
    }
    return true;
  }
}
