package edu.praktikum.sprint7;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static edu.praktikum.sprint7.CONSTANT.Constants.BASE_URL;
import static edu.praktikum.sprint7.CONSTANT.Constants.CREATE_ORDER_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.*;

public class GetOrdersTest {

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Checking API orders")
    @Description("Getting a list of orders")
    public void getOrdersList() {
        given()
                .when()
                .get(CREATE_ORDER_ENDPOINT)
                .then()
                .statusCode(SC_OK)
                .and()
                .body("orders", notNullValue())
                .body("orders.size()", greaterThan(0));
    }

}