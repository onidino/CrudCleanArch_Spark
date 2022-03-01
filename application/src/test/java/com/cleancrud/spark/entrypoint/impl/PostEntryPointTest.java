package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.PostEntryPoint;
import com.cleancrud.spark.utils.JsonTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Response;
import use_cases.CreateRecordUseCase;
import utils.BaseUnitTest;
import utils.RequestMock;
import utils.ResponseMock;

class PostEntryPointTest extends BaseUnitTest {

  private RequestMock request;
  private ResponseMock response;
  private final JsonTransformer jsonTransformer = new JsonTransformer();

  @Mock
  private CreateRecordUseCase createRecordUseCase;

  @InjectMocks
  private PostEntryPoint postEntryPoint;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
    postEntryPoint = new PostEntryPoint(createRecordUseCase, jsonTransformer);
    request = new RequestMock();
    response = new ResponseMock();
  }

  @Test
  void whenPutRequestThenOk() {
    Response result = postEntryPoint.internalHandle(request, response);

    Assertions.assertNotNull(result);
    Assertions.assertEquals("{\"result\":\"POST RESPONSE\"}", result.body());
  }
}