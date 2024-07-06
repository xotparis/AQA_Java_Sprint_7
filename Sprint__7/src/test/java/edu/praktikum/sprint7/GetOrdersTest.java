package edu.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static edu.praktikum.sprint7.CONSTANT.Constants.BASE_URL;
import static edu.praktikum.sprint7.GetOrders.check;
import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.*;

public class GetOrdersTest extends BaseTest {

    @Test
    @DisplayName("Checking API orders")
    @Description("Getting a list of orders")
    public void testCheckOrders() {
        Response response = check();
        assertNotNull("Response should not be null", response);
        assertEquals("Status code should be 200", SC_OK, response.getStatusCode());
        assertTrue("Orders should not be empty", response.jsonPath().getList("orders").size() > 0);
    }
}