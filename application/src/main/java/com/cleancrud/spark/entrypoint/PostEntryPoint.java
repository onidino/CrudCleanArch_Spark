package com.cleancrud.spark.entrypoint;

import com.cleancrud.spark.entrypoint.dto.RecordDto;
import com.cleancrud.spark.entrypoint.mapper.RecordMapper;
import com.cleancrud.spark.utils.JsonTransformer;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;
import spark.Request;
import spark.Response;
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
    Objects.requireNonNull(request.body(), "body cant be empty");
    RecordDto recordData = deserialize(request.body(), RecordDto.class);

    try {
      RecordDto result = RecordMapper.entityToDto(
          createRecordUseCase.execute(recordData.getData()));

      response.body(serialize(result));
    } catch (Exception e) {
      e.printStackTrace();
    }

    return response;
  }
}
