package edu.praktikum.sprint7;

import edu.praktikum.sprint7.models.Courier;
import edu.praktikum.sprint7.models.CourierCreds;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.http.auth.Credentials;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static edu.praktikum.sprint7.CONSTANT.Constants.*;
import static edu.praktikum.sprint7.CreateCourier.login;
import static edu.praktikum.sprint7.courier.CourierGenerator.randomCourier;
import static edu.praktikum.sprint7.models.CourierCreds.credsFromCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertEquals;

public class AuthorizationCourierTests {

    private Integer courierId = null;

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    @DisplayName("Authorization courier") // имя теста
    @Description("Positive case: Authorization courier(Code 200)") // описание теста
    public void authorizationCourier() {
        Courier courier = randomCourier();

        Response response = CreateCourier.create(courier);
        assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        response.then().assertThat().body("ok", equalTo(true));

        Response loginResponse = login(credsFromCourier(courier));
        courierId = loginResponse.path("id");
        assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
    }

    @Test
    @DisplayName("Authorization courier without login") // имя теста
    @Description("Negative case: Authorization courier without login(Code 400 and message)") // описание теста
    public void authorizationCourierWithoutMandatoryFields() {

        Courier courier = randomCourier();
        courier.setLogin("");
        Response loginResponse = CreateCourier.login(credsFromCourier(courier));

        Assert.assertEquals("Неверный статус код", SC_BAD_REQUEST, loginResponse.statusCode());
        loginResponse.then().assertThat().body("message", equalTo("Недостаточно данных для входа"));

    }

    @Test
    @DisplayName("Authorization with non-existent user") // имя теста
    @Description("Negative case: Authorization with non-existent user (Code 404 and message)") // описание теста
    public void authorizationNonExistentUser() {
        // Создание объекта с несуществующими учетными данными
        Courier courier = randomCourier();
        courier.setLogin("nonExistentLogin");
        courier.setPassword("nonExistentPassword");

        Response login = login(credsFromCourier(courier));

        // Проверка, что возвращается правильный статус код и сообщение об ошибке
        assertEquals("Неверный статус код", SC_NOT_FOUND, login.statusCode());
        login.then().assertThat().body("message", equalTo("Учетная запись не найдена"));

    }

    @After
    public void tearDown() {

        if (courierId != null) {
            CreateCourier.delete(courierId);
        }

    }
}
