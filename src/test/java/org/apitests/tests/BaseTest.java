package org.apitests.tests;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class BaseTest {

    @BeforeAll
    protected static void setUp() {
        RestAssured.filters(new AllureRestAssured());
    }
    @AfterAll
    protected void tearDown() {
        RestAssured.reset();
    }
}
