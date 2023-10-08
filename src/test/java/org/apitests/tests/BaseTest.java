package org.apitests.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.helpers.ApiSpec;
import org.helpers.ConfigReader;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {
//    protected static final RequestSpecification REQUEST_SPEC = ApiSpec.requestSpec;
//    protected static final ResponseSpecification RESPONSE_SPEC = ApiSpec.responseSpec;

    @BeforeAll
    protected static void setUp(){
        RestAssured.filters(new AllureRestAssured());
//        RestAssured.requestSpecification = REQUEST_SPEC;
//        RestAssured.responseSpecification = RESPONSE_SPEC;
    }
    @AfterAll
    protected void tearDown() {
        RestAssured.reset();
    }
}
