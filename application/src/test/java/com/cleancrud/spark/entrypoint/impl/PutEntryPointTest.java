package com.cleancrud.spark.entrypoint.impl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import com.cleancrud.spark.entrypoint.PutEntryPoint;
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
import use_cases.UpdateRecordByIdUseCase;
import utils.BaseUnitTest;
import utils.RequestMock;
import utils.ResponseMock;

class PutEntryPointTest extends BaseUnitTest {

  private RequestMock request;
  private ResponseMock response;
  private final JsonTransformer jsonTransformer = new JsonTransformer();

  @Mock
  private UpdateRecordByIdUseCase updateRecordByIdUseCase;

  @InjectMocks
  private PutEntryPoint putEntryPoint;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
    putEntryPoint = new PutEntryPoint(updateRecordByIdUseCase, jsonTransformer);
    request = new RequestMock();
    response = new ResponseMock();
  }

  @Test
  void whenPutRequestThenOk() throws UseCaseException {
    request.addParam("id", "1234");
    request.body("{\"id\":1234,\"record_data\":\"updated_info\"}");

    when(updateRecordByIdUseCase.execute(anyLong(), anyString()))
        .thenReturn(new Record(1234L, "updated_info"));

    Response result = putEntryPoint.internalHandle(request, response);

    Assertions.assertNotNull(result);
    Assertions.assertEquals("{\"id\":1234,\"record_data\":\"updated_info\"}", result.body());
  }

  @Test
  void whenPutRequestThenThrowsException() throws UseCaseException {
    request.addParam("id", "1234");
    request.body("{\"id\":1234,\"record_data\":\"updated_info\"}");

    when(updateRecordByIdUseCase.execute(anyLong(), anyString()))
        .thenThrow(new UseCaseException("UPDATE: Could not update record with id [1234]"));

    Response result = putEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class exception.UseCaseException\",\"message\":\"UPDATE: Could not update record with id [1234]\"}",
        result.body());
  }

  @Test
  void whenPutRequestWithNullIdThenThrowsException() {
    request.addParam("id", null);

    Response result = putEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"id cant be null\"}",
        result.body());
  }

  @Test
  void whenPutRequestWithNullDataThenThrowsException() {
    request.addParam("id", "1234");
    request.body("{\"id\":1234,\"record_data\":null}");

    Response result = putEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"field data in body cant be null\"}",
        result.body());
  }

  @Test
  void whenPutRequestWithNullBodyThenThrowsException() {
    request.addParam("id", "1234");

    Response result = putEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"body cant be empty\"}",
        result.body());
  }

}