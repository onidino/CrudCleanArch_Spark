package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import com.cleancrud.spark.entrypoint.mapper.RecordMapper;
import com.cleancrud.spark.utils.JsonTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
import spark.utils.Assert;
import use_cases.CreateRecordUseCase;

/**
 * POST entrypoint.
 */
@Singleton
public class PostEntryPoint extends EntryPoint {

  private final CreateRecordUseCase createRecordUseCase;

  @Inject
  public PostEntryPoint(final CreateRecordUseCase createRecordUseCase, JsonTransformer json) {
    super(json);
    this.createRecordUseCase = createRecordUseCase;
  }

  @Override
  public Response internalHandle(Request request, Response response) {
    if (!entryValidations(request, response)) {
      return response;
    }
    try {
      RecordDto recordData = deserialize(request.body(), RecordDto.class);
      RecordDto result = RecordMapper.entityToDto(
          createRecordUseCase.execute(recordData.getRecordData()));
      response.body(serialize(result));
    } catch (Exception e) {
      responseException(response, e);
    }
    return response;
  }

  @Override
  public boolean entryValidations(Request request, Response response) {
    try {
      Assert.notNull(request.body(), "body cant be empty");
      RecordDto recordDto = deserialize(request.body(), RecordDto.class);
      Assert.notNull(recordDto.getRecordData(), "field data in body cant be null");
    } catch (IllegalArgumentException e) {
      responseException(response, e);
      return false;
    }
    return true;
  }

}
