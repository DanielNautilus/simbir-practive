package org.apitests.steps;

import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apitests.models.Entity;
import org.apitests.models.EntitySearchTerm;
import org.helpers.ApiSpec;

import java.lang.reflect.Field;


public class EntitiesSteps {
    private static final String CREATE_ENDPOINT = "/create";
    private static final String DELETE_ENDPOINT = "/delete/";
    private static final String GET_ENDPOINT = "/get/";
    private static final String GET_ALL_ENDPOINT = "/getAll";
    private static final String PATCH_ENDPOINT = "/patch/";

    @Step("Add entity {entity}")
    public static Response createEntity(Entity entity) {
        RequestSpecification requestSpec = ApiSpec.requestSpecSetJsonGetJson();

        return RestAssured.given()
                .spec(requestSpec)
                .body(entity)
                .when()
                .post(CREATE_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }

    @Step("Get entity with ID:{entityId}")
    public static Response getEntity(Integer entityId) {
        RequestSpecification requestSpec = ApiSpec.requestSpecSetJsonGetJson();
        return RestAssured.given()
                .spec(requestSpec)
                .when()
                .get(GET_ENDPOINT + entityId)
                .then()
                .extract()
                .response();
    }

    @Step("Update entity with ID:{entityId}")
    public static Response updateEntity(Integer entityId, Entity entity) {
        RequestSpecification requestSpec = ApiSpec.requestSpecSetJsonGetJson();
        return RestAssured.given()
                .spec(requestSpec)
                .body(entity)
                .when()
                .patch(PATCH_ENDPOINT + entityId)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }

    @Step("Delete entity with ID:{entityId}")
    public static Response deleteEntity(Integer entityId) {
        RequestSpecification requestSpec = ApiSpec.requestSpecSetJsonGetJson();
        return RestAssured.given()
                .spec(requestSpec)
                .when()
                .delete(DELETE_ENDPOINT + entityId)
                .then()
                .statusCode(204)
                .extract()
                .response();
    }

    @Step("Search entity")
    public static Response getAllEntities(EntitySearchTerm searchTerm) {
        RequestSpecification requestSpec = ApiSpec.requestSpecSetJsonGetJson();
        RequestSpecification request = RestAssured.given().spec(requestSpec);
        addSearchParamsToRequest(request, searchTerm);

        return request
                .when()
                .get(GET_ALL_ENDPOINT)
                .then()
                .extract()
                .response();
    }

    private static void addSearchParamsToRequest(RequestSpecification request, Object searchTerm) {
        if (searchTerm != null) {
            Class<?> searchTermClass = searchTerm.getClass();
            Field[] fields = searchTermClass.getDeclaredFields();

            for (Field field : fields) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(searchTerm);

                    if (value != null) {
                        request.queryParam(field.getName(), value);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
