package org.apitests.steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apitests.models.Entity;
import org.helpers.ApiSpec;
import org.helpers.ConfigReader;
import org.apitests.models.EntitySearchTerm;

import java.lang.reflect.Field;

public class EntitiesSteps {
    private static final String CREATE_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.create");
    private static final String DELETE_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.delete");
    private static final String GET_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.get");
    private static final String GET_ALL_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.getAll");
    private static final String PATCH_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.patch");

    private static final RequestSpecification requestSpecSetJsonGetJson = ApiSpec.requestSpecSetJsonGetJson;
    private static final RequestSpecification requestSpecSetJsonGetText = ApiSpec.requestSpecSetJsonGetText;
    @Step("Add entity {entity}")
    public static synchronized Response createEntity( Entity entity) {
        return requestSpecSetJsonGetText
                .body(entity)
                .when()
                .post(CREATE_ENDPOINT)
                .then()
                .statusCode(200)
                .extract()
                .response();
    }
    @Step("Get entity with ID:{entityId}")
    public static synchronized Response getEntity(Integer entityId) {
        return requestSpecSetJsonGetJson
                .when()
                    .get(GET_ENDPOINT + entityId)
                .then()
                    .extract()
                    .response();
    }
    @Step("Update entity with ID:{entityId}")
    public static synchronized Response updateEntity( Integer entityId, Entity entity) {
        return requestSpecSetJsonGetJson
                    .body(entity)
                .when()
                    .patch(PATCH_ENDPOINT + entityId)
                .then()
                    .statusCode(204)
                    .extract()
                    .response();
    }
    @Step("Delete entity with ID:{entityId}")
    public static synchronized Response deleteEntity(Integer entityId) {
     return requestSpecSetJsonGetText
            .when()
                .delete(DELETE_ENDPOINT + entityId)
            .then()
                .statusCode(204)
                .extract()
                .response();
    }
    @Step("Search entity")
    public static synchronized Response getAllEntities( EntitySearchTerm searchTerm) {
        RequestSpecification request = requestSpecSetJsonGetJson;
        addSearchParamsToRequest(request, searchTerm);

        return request
                .when()
                    .get(GET_ALL_ENDPOINT)
                .then()
                    .extract()
                    .response();

    }
    private static synchronized void addSearchParamsToRequest(RequestSpecification request, Object searchTerm) {
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
