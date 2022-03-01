package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import com.cleancrud.spark.entrypoint.mapper.RecordMapper;
import com.cleancrud.spark.utils.JsonTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.utils.Assert;
import use_cases.UpdateRecordUseCase;

/**
 * PUT entrypoint.
 */
@Singleton
public class PutEntryPoint extends EntryPoint {

  private final UpdateRecordUseCase updateRecordUseCase;

  @Inject
  public PutEntryPoint(final UpdateRecordUseCase updateRecordUseCase, JsonTransformer json) {
    super(json);
    this.updateRecordUseCase = updateRecordUseCase;
  }

  @Override
  public Response internalHandle(Request request, Response response) {
    if (!initialValidations(request, response)) {
      return response;
    }
    try {
      RecordDto recordDto = deserialize(request.body(), RecordDto.class);
      RecordDto result = RecordMapper.entityToDto(
          updateRecordUseCase.execute(recordDto.getId(), recordDto.getData()));
      response.body(serialize(result));
    } catch (Exception e) {
      responseException(response, e);
    }

    return response;
  }

  @Override
  public boolean initialValidations(Request request, Response response) {
    try {
      Assert.notNull(request.params("id"), "id cant be null");
      Assert.notNull(request.body(), "body cant be empty");
      RecordDto recordDto = deserialize(request.body(), RecordDto.class);
      Assert.notNull(recordDto.getData(), "field 'data' in body cant be null");
    } catch (IllegalArgumentException e) {
      responseException(response, e);
      return false;
    }
    return true;
  }
}
