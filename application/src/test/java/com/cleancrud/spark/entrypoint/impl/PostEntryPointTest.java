package com.cleancrud.spark.entrypoint.impl;

import static org.mockito.Mockito.when;

import com.cleancrud.spark.entrypoint.PostEntryPoint;
import com.cleancrud.spark.utils.JsonTransformer;
import entity.Record;
import exception.UseCaseException;
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
  void whenPostRequestThenOk() throws UseCaseException {
    request.body("{\"data\":\"test\"}");

    when(createRecordUseCase.execute("test"))
        .thenReturn(new Record(1234L, "test"));

    Response result = postEntryPoint.internalHandle(request, response);

    Assertions.assertNotNull(result);
    Assertions.assertEquals("{\"id\":1234,\"data\":\"test\"}", result.body());
  }

  @Test
  void whenPostRequestThenThrowsException() throws UseCaseException {
    request.body("{\"data\":\"test\"}");

    when(createRecordUseCase.execute("test"))
        .thenThrow(new UseCaseException("CREATE: Could not create record"));

    Response result = postEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class exception.UseCaseException\",\"message\":\"CREATE: Could not create record\"}",
        result.body());
  }

  @Test
  void whenPostRequestWithNullDataThenThrowsException() {
    request.body("{\"data\":null}");

    Response result = postEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"field data in body cant be null\"}",
        result.body());
  }

  @Test
  void whenPostRequestWithNullBodyThenThrowsException() {
    Response result = postEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"body cant be empty\"}",
        result.body());
  }
}