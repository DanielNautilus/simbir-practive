package org.helpers;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.given;

public class ApiSpec {
    private static final String BASE_URL = ConfigReader.getProperty("test.api.url");

    public static RequestSpecification requestSpecSetJsonGetJson = given()
            .baseUri(BASE_URL)
            .header("Accept", "application/json")
            .contentType(ContentType.JSON);

    public static RequestSpecification requestSpecSetJsonGetText = given()
            .baseUri(BASE_URL)
            .header("Accept", "text/plain")
            .contentType(ContentType.JSON);


    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .log(LogDetail.BODY)
            .build();
}
