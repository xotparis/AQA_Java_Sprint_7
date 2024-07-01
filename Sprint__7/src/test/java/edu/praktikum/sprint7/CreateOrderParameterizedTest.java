package edu.praktikum.sprint7;

import edu.praktikum.sprint7.models.*;
import edu.praktikum.sprint7.utils.Utils;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static edu.praktikum.sprint7.CONSTANT.Constants.*;
import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.hamcrest.CoreMatchers.notNullValue;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest {

    @Parameterized.Parameter
    public String[] colors;

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new String[]{"BLACK"}},
                {new String[]{"GREY"}},
                {new String[]{"BLACK", "GREY"}},
                {new String[]{}}
        });
    }

    @Before
    public void setUp() {
        RestAssured.baseURI = BASE_URL;
    }
    @DisplayName("Create order with and without colors")
    @Test
    public void createOrderWithColors() {
        Order order = new Order(Utils.randomFirstNames(), Utils.randomSurname(), Utils.randomString(12), Utils.getRandomNumber(), Utils.phoneNumber(), Utils.getRandomNumber(), Utils.getRandomDate(), Utils.randomString(12), colors);

        given()
                .header("Content-Type", "application/json")
                .and()
                .body(order)
                .when()
                .post(CREATE_ORDER_ENDPOINT)
                .then()
                .statusCode(SC_CREATED)
                .and()
                .body("track", notNullValue());
    }
}
