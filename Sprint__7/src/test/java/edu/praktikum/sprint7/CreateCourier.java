package edu.praktikum.sprint7;

import edu.praktikum.sprint7.models.Courier;
import edu.praktikum.sprint7.models.CourierCreds;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static edu.praktikum.sprint7.CONSTANT.Constants.*;
import static io.restassured.RestAssured.given;

public final class CreateCourier {

    @Step("Courier registration")
    public static Response create(Courier courier) {
        return given()
                .header("Content-type", "application/json")
                .body(courier)
                .when()
                .post(CREATE_ENDPOINT);
    }
    @Step("Courier authorization")
    public static Response login(CourierCreds creds) {
        return given()
                .header("Content-type", "application/json")
                .body(creds)
                .when()
                .post(LOGIN_ENDPOINT);
    }
    @Step("Removing a courier")
    public static Response delete(int id) {
        return given()
                .header("Content-type", "application/json")
                .pathParam("id", id)
                .when()
                .delete(DELETE_ENDPOINT);
    }

}
