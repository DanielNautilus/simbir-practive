package org.helpers;

import io.restassured.response.Response;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.models.IdentifiableModel;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class EntitiesHelper {
    public static Integer getMaxEntityId(List<Entity> entities) {
        return getMaxId(entities);
    }

    public static Integer getMaxAdditionIdFromEntities(List<Entity> entities) {
        return getMaxAdditionId(getAdditionFromEntities(entities));
    }

    private static Integer getMaxAdditionId(List<Addition> additions) {
        return getMaxId(additions);
    }

    private static List<Addition> getAdditionFromEntities(List<Entity> entities) {
        List<Addition> additions = new ArrayList<>();
        for (Entity entity : entities) {
            if (entity.getAddition() != null) {
                additions.add(entity.getAddition());
            }
        }
        return additions;
    }

    private static Integer getMaxId(List<? extends IdentifiableModel> models) {
        return models.stream()
                .map(IdentifiableModel::getId)
                .filter(Objects::nonNull)
                .max(Comparator.comparingInt(Integer::intValue))
                .orElse(0);
    }
    public static List<Entity> getAllEntitiesFormResponse(Response response){
        return response.jsonPath().getList("entity", Entity.class);
    }
    public static Integer getEntityIdFormResponse(Response response){
        return Integer.parseInt(response
                .body()
                .asString());
    }
    public static Entity getEntityFormResponse(Response response){
        return response
                .body()
                .as(Entity.class);
    }
}
