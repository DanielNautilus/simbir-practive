package org.helpers;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.helpers.ConfigReader;

public class ApiSpec {
    private static final String BASE_URL = ConfigReader.getProperty("test.api.url");

    public static RequestSpecification requestSpecSetJsonGetJson() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Accept", "application/json")
                .setContentType(ContentType.JSON)
                .build();
    }
    public static RequestSpecification createRequestSpecSetJsonGetText() {
        return new RequestSpecBuilder()
                .setBaseUri(BASE_URL)
                .addHeader("Accept", "text/plain")
                .setContentType(ContentType.JSON)
                .build();
    }


//    public static ThreadLocal<RequestSpecification> requestSpecSetJsonGetJson = ThreadLocal.withInitial(() ->
//            new RequestSpecBuilder()
//                    .setBaseUri(BASE_URL)
//                    .addHeader("Accept", "application/json")
//                    .setContentType(ContentType.JSON)
//                    .build()
//    );
//
//    public static ThreadLocal<RequestSpecification> requestSpecSetJsonGetText = ThreadLocal.withInitial(() ->
//            new RequestSpecBuilder()
//                    .setBaseUri(BASE_URL)
//                    .addHeader("Accept", "text/plain")
//                    .setContentType(ContentType.JSON)
//                    .build()
//    );
//    public static RequestSpecification givenJsonGetJson() {
//        return requestSpecSetJsonGetJson.get();
//    }
//
//    public static RequestSpecification givenJsonGetText() {
//        return requestSpecSetJsonGetText.get();
//    }
}
