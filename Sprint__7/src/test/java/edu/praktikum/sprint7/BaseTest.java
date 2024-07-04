package edu.praktikum.sprint7;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.junit.BeforeClass;

import static edu.praktikum.sprint7.CONSTANT.Constants.BASE_URL;

public class BaseTest {

    protected static RequestSpecification spec;
    @BeforeClass
    public static void setUp() {
        RestAssured.baseURI = BASE_URL;
        spec = RestAssured.given()
                .header("Content-Type", "application/json");
    }
}
