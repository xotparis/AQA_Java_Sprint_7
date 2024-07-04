package edu.praktikum.sprint7;

import edu.praktikum.sprint7.models.Courier;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;


import static edu.praktikum.sprint7.courier.CourierGenerator.randomCourier;
import static edu.praktikum.sprint7.models.CourierCreds.credsFromCourier;
import static org.apache.http.HttpStatus.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class CreateCourierTests extends BaseTest {

    private Integer courierId = null;

    @Test
    @DisplayName("Create courier") // имя теста
    @Description("Positive case: Create courier(Code 201)") // описание теста
    public void createCourier() {
        Courier courier = randomCourier();

        Response response = CreateCourier.create(courier);
        Assert.assertEquals("Неверный статус код", SC_CREATED, response.statusCode());
        response.then().assertThat().body("ok", equalTo(true));

        Response loginResponse = CreateCourier.login(credsFromCourier(courier));
        courierId = loginResponse.path("id");
        Assert.assertEquals("Неверный статус код", SC_OK, loginResponse.statusCode());
    }

    @Test
    @DisplayName("Create courier twice") // имя теста
    @Description("Negative case: Create courier twice (Code 409 and message)") // описание теста
    public void createTwiceCourier() {
        Courier courier = randomCourier();

        Response responseFirstCreation = CreateCourier.create(courier);
        Assert.assertEquals("Неверный статус код", SC_CREATED, responseFirstCreation.statusCode());
        responseFirstCreation.then().assertThat().body("ok", equalTo(true));

        Response responseSecondCreation = CreateCourier.create(courier);
        Response loginResponse = CreateCourier.login(credsFromCourier(courier));
        courierId = loginResponse.path("id");
        Assert.assertEquals("Неверный статус код", SC_CONFLICT, responseSecondCreation.statusCode());
        responseSecondCreation.then().assertThat().body("message", equalTo("Этот логин уже используется. Попробуйте другой."));
    }

    @Test
    @DisplayName("Create courier without login") // имя теста
    @Description("Negative case: Create courier without login (Code 400 and message)")
    public void createCourierWithoutMandatoryFields() {
        // Создаем курьера без логина
        Courier courierWithoutLogin = new Courier(randomCourier().getFirstName(), randomCourier().getPassword(), "");

        Response response = CreateCourier.create(courierWithoutLogin);
        Assert.assertEquals("Неверный статус код", SC_BAD_REQUEST, response.statusCode());
        response.then().assertThat().body("message", equalTo("Недостаточно данных для создания учетной записи"));

    }

    @After
    public void tearDown() {

        if (courierId != null) {
            CreateCourier.delete(courierId);
        }

    }

}