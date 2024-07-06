package edu.praktikum.sprint7;

import edu.praktikum.sprint7.models.Order;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static edu.praktikum.sprint7.CONSTANT.Constants.CREATE_ORDER_ENDPOINT;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

public final class CreateOrder {

    @Step("Creating an order")
    public static Response createOrder(Order order) {
        return given(BaseTest.spec)
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT)
                .then()
                .statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue())
                .extract()
                .response(); // Сохраняем результат в переменную Response и возвращаем его
    }
}
