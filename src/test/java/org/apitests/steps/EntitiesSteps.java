package org.apitests.steps;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apitests.models.Entity;
import org.helpers.ApiSpec;
import org.helpers.ConfigReader;
import org.apitests.models.EntitySearchTerm;

import java.lang.reflect.Field;
import java.util.List;

public class EntitiesSteps {
    private static final String CREATE_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.create");
    private static final String DELETE_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.delete");
    private static final String GET_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.get");
    private static final String GET_ALL_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.getAll");
    private static final String PATCH_ENDPOINT = ConfigReader.getProperty("test.api.entityEndpoints.patch");

    private static final RequestSpecification requestSpecSetJsonGetJson = ApiSpec.requestSpecSetJsonGetJson;
    private static final RequestSpecification requestSpecSetJsonGetText = ApiSpec.requestSpecSetJsonGetText;
    public static Integer createEntity( Entity entity) {
        String responseText = requestSpecSetJsonGetText
                    .body(entity)
                .when()
                    .post(CREATE_ENDPOINT)
                .then()
                    .statusCode(200)
                    .extract()
                    .response()
                    .body()
                    .asString();
        return Integer.parseInt(responseText);
    }
    public static Entity getEntity(Integer entityId) {
        return requestSpecSetJsonGetJson
                .when()
                    .get(GET_ENDPOINT + entityId)
                .then()
                    .extract()
                    .response()
                    .as(Entity.class);
    }
    public static void updateEntity( Integer entityId, Entity entity) {
        requestSpecSetJsonGetText
                    .body(entity)
                .when()
                    .patch(PATCH_ENDPOINT + entityId)
                .then()
                    .statusCode(204)
                    .extract()
                    .response();
    }
    public static void deleteEntity(Integer entityId) {
     requestSpecSetJsonGetText
            .when()
                .delete(DELETE_ENDPOINT + entityId)
            .then()
                .statusCode(204)
                .extract()
                .response();
    }
    public static List<Entity> getAllEntities( EntitySearchTerm searchTerm) {
        RequestSpecification request = requestSpecSetJsonGetJson;
        addSearchParamsToRequest(request, searchTerm);

        return request
                .when()
                    .get(GET_ALL_ENDPOINT)
                .then()
                    .extract()
                    .response()
                        .jsonPath()
                        .getList("entity", Entity.class);
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
    public static void deleteAllEntities() {
        List<Entity> entities = getAllEntities(null);

        for (Entity entity : entities) {
            deleteEntity(entity.getId());
        }
    }
}
