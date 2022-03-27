package com.cleancrud.spark.entrypoint.impl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

import com.cleancrud.spark.entrypoint.GetEntryPoint;
import com.cleancrud.spark.utils.JsonTransformer;
import entity.Record;
import exception.UseCaseException;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Response;
import use_cases.GetRecordByIdUseCase;
import utils.BaseUnitTest;
import utils.RequestMock;
import utils.ResponseMock;

class GetEntryPointTest extends BaseUnitTest {

  private RequestMock request;
  private ResponseMock response;
  private final JsonTransformer jsonTransformer = new JsonTransformer();

  @Mock
  private GetRecordByIdUseCase getRecordByIdUseCase;

  @InjectMocks
  private GetEntryPoint getEntryPoint;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
    getEntryPoint = new GetEntryPoint(getRecordByIdUseCase, jsonTransformer);
    request = new RequestMock();
    response = new ResponseMock();
  }

  @Test
  void whenGetRequestThenOk() throws UseCaseException {
    request.addParam("id", "1234");
    when(getRecordByIdUseCase.execute(anyLong()))
        .thenReturn(new Record(1234L, "test"));

    Response result = getEntryPoint.internalHandle(request, response);

    Assertions.assertNotNull(result);
    Assertions.assertEquals("{\"id\":1234,\"data\":\"test\"}", result.body());
  }

  @Test
  void whenGetRequestWithIdNullThenThrowsException() {
    request.addParam("id", null);

    Response result = getEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"id cant be null\"}",
        result.body());
  }

  @Test
  void whenGetRequestThenThrowsException() throws UseCaseException {
    request.addParam("id", "1234");
    when(getRecordByIdUseCase.execute(anyLong()))
        .thenThrow(new UseCaseException("GET: Record not found for id [1234]"));

    Response result = getEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class exception.UseCaseException\",\"message\":\"GET: Record not found for id [1234]\"}",
        result.body());
  }

  @Test
  void whenGetRequestThenEntryPointHandleTestOk() throws UseCaseException {
    request.setRequestMethod("GET");
    request.setUri("test_uri");
    request.addParam("id", "1234");

    when(getRecordByIdUseCase.execute(anyLong()))
        .thenReturn(new Record(1234L, "test"));

    Response result = getEntryPoint.handle(request, response);

    Assertions.assertNotNull(result);
    Assertions.assertEquals(HttpStatus.OK_200, result.status());
    Assertions.assertEquals("application/json", result.type());
    Assertions.assertEquals("{\"id\":1234,\"data\":\"test\"}", result.body());
  }
}