package org.apitests.tests;

import io.qameta.allure.*;
import io.restassured.response.Response;
import org.apitests.asserts.EntityAsserts;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.models.EntitySearchTerm;
import org.apitests.steps.EntitiesSteps;
import org.helpers.EntitiesHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
@Epic("Entities Management")
@Feature("Getting All")
@DisplayName("Getting all entities tests")
@Execution(ExecutionMode.CONCURRENT)
public class GettingEntitiesAllTests extends BaseTest {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Getting verified entities")
    @Description("Entities exist")
    public void testGettingAllVerifiedEntities() {
        // Pre-condition
        Entity firstEntity = new Entity("Entity1",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});
        Entity secondEntity = new Entity("Entity2",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});

        Integer firstEntityId = EntitiesHelper.getEntityIdFormResponse(EntitiesSteps.createEntity(firstEntity));
        Integer secondEntityId = EntitiesHelper.getEntityIdFormResponse(EntitiesSteps.createEntity(secondEntity));

        // Act
        EntitySearchTerm searchTerm = new EntitySearchTerm();
        searchTerm.setVerified(true);

        Response response = EntitiesSteps.getAllEntities(searchTerm);
        List<Entity> allEntities =EntitiesHelper.getAllEntitiesFormResponse(response);

        // Assert
        boolean isFirstEntityFound = false;
        boolean isSecondEntityFound = false;
        for (Entity entity : allEntities) {
            if (entity.getId().equals(firstEntityId)) {
                isFirstEntityFound = true;
                EntityAsserts.assertEntityEquivalent(firstEntity, entity);
            }
            if (entity.getId().equals(secondEntityId)) {
                isSecondEntityFound = true;
                EntityAsserts.assertEntityEquivalent(secondEntity, entity);
            }
        }

        assertTrue(isFirstEntityFound);
        assertTrue(isSecondEntityFound);
        assertEquals(200, response.statusCode());
        assertNotNull(allEntities);
        assertTrue(allEntities.size() >= 2);

        // Post-action: Удаляем созданные сущности после теста
         EntitiesSteps.deleteEntity(firstEntityId);
         EntitiesSteps.deleteEntity(secondEntityId);
    }
}
