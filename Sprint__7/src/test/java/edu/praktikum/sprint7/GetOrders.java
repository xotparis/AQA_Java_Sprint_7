package edu.praktikum.sprint7;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import static edu.praktikum.sprint7.CONSTANT.Constants.CREATE_ORDER_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.Matchers.greaterThan;

public final class GetOrders {

    @Step("Checking orders from the endpoint")
    public static Response check() {
        return given(BaseTest.spec)
                .when()
                .get(CREATE_ORDER_ENDPOINT)
                .then()
                .statusCode(200)
                .and()
                .body("orders", notNullValue())
                .body("orders.size()", greaterThan(0))
                .extract().response();
    }
}
