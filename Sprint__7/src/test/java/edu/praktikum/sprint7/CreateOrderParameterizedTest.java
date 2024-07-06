package edu.praktikum.sprint7;

import edu.praktikum.sprint7.models.*;
import edu.praktikum.sprint7.utils.Utils;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.apache.http.HttpStatus.SC_CREATED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class CreateOrderParameterizedTest extends BaseTest{

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

    @DisplayName("Create order with and without colors")
    @Test
    public void createOrderWithColors() {
        Order order = new Order(Utils.randomFirstNames(), Utils.randomSurname(), Utils.randomString(12), Utils.getRandomNumber(), Utils.phoneNumber(), Utils.getRandomNumber(), Utils.getRandomDate(), Utils.randomString(12), colors);

        Response response = CreateOrder.createOrder(order);
        assertNotNull("Response should not be null", response);
        assertEquals("Status code should be 201", SC_CREATED, response.getStatusCode());
        assertNotNull("Track should not be null", response.path("track"));
    }
}
