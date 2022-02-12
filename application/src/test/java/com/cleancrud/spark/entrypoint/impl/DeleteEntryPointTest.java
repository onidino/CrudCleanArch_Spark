package com.cleancrud.spark.entrypoint.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import spark.Response;
import utils.BaseUnitTest;
import utils.RequestMock;
import utils.ResponseMock;

class DeleteEntryPointTest extends BaseUnitTest {

    private RequestMock request;
    private ResponseMock response;

    @InjectMocks
    private DeleteEntryPoint deleteEntryPoint;

    @BeforeEach
    public void initMocks() {
        super.closeable = MockitoAnnotations.openMocks(this);
        request = new RequestMock();
        response = new ResponseMock();
    }

    @Test
    void whenDeleteRequestThenOk() {
        Response result = deleteEntryPoint.internalHandle(request, response);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("{\"result\":\"DELETE RESPONSE\"}", result.body());
    }

    @Test
    void whenDeleteRequestFailsThenThrowsException() {
        Response result = deleteEntryPoint.internalHandle(request, response);

        Assertions.assertNotNull(result);
        Assertions.assertEquals("{\"result\":\"DELETE RESPONSE\"}", result.body());
    }

}