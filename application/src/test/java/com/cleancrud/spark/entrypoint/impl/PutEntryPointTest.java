package com.cleancrud.spark.entrypoint.impl;

import com.cleancrud.spark.entrypoint.PutEntryPoint;
import com.cleancrud.spark.utils.JsonTransformer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import spark.Response;
import use_cases.UpdateRecordUseCase;
import utils.BaseUnitTest;
import utils.RequestMock;
import utils.ResponseMock;

class PutEntryPointTest extends BaseUnitTest {

  private RequestMock request;
  private ResponseMock response;
  private final JsonTransformer jsonTransformer = new JsonTransformer();

  @Mock
  private UpdateRecordUseCase updateRecordUseCase;

  @InjectMocks
  private PutEntryPoint putEntryPoint;

  @BeforeEach
  public void initMocks() {
    super.closeable = MockitoAnnotations.openMocks(this);
    putEntryPoint = new PutEntryPoint(updateRecordUseCase, jsonTransformer);
    request = new RequestMock();
    response = new ResponseMock();
  }

  @Test
  void whenPutRequestThenOk() {
    Response result = putEntryPoint.internalHandle(request, response);

    Assertions.assertNotNull(result);
    Assertions.assertEquals("{\"result\":\"PUT RESPONSE\"}", result.body());
  }
}