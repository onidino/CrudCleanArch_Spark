package com.cleancrud.spark.entrypoint.impl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;

import com.cleancrud.spark.entrypoint.DeleteEntryPoint;
import com.cleancrud.spark.utils.JsonTransformer;
import exception.UseCaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import spark.Response;
import use_cases.DeleteRecordByIdUseCase;
import utils.BaseUnitTest;
import utils.RequestMock;
import utils.ResponseMock;

class DeleteEntryPointTest extends BaseUnitTest {

  private RequestMock request;
  private ResponseMock response;
  private final JsonTransformer jsonTransformer = new JsonTransformer();

  @Mock
  private DeleteRecordByIdUseCase deleteRecordByIdUseCase;

  @InjectMocks
  private DeleteEntryPoint deleteEntryPoint;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
    deleteEntryPoint = new DeleteEntryPoint(deleteRecordByIdUseCase, jsonTransformer);
    request = new RequestMock();
    response = new ResponseMock();
  }

  @Test
  void whenDeleteRequestThenOk() throws UseCaseException {
    request.addParam("id", "1234");
    doNothing().when(deleteRecordByIdUseCase).execute(anyLong());

    Assertions.assertDoesNotThrow(() ->
        deleteEntryPoint.internalHandle(request, response));

    Mockito.verify(deleteRecordByIdUseCase, times(1)).execute(anyLong());
  }

  @Test
  void whenDeleteWithNullIdRequestThenOk() {
    request.addParam("id", null);

    Response result = deleteEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class java.lang.IllegalArgumentException\",\"message\":\"id cant be null\"}",
        result.body());
  }


  @Test
  void whenDeleteRequestThenThrowsException() throws UseCaseException {
    request.addParam("id", "1234");

    doThrow(new UseCaseException("DELETE: Could not delete record with id [1234]"))
        .when(deleteRecordByIdUseCase).execute(anyLong());

    Response result = deleteEntryPoint.internalHandle(request, response);

    Assertions.assertEquals(
        "{\"exception\":\"class exception.UseCaseException\",\"message\":\"DELETE: Could not delete record with id [1234]\"}",
        result.body());
  }
}