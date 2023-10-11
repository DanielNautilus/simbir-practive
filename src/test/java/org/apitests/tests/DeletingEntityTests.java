package org.apitests.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.steps.EntitiesSteps;
import org.helpers.EntitiesHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("Entities Management")
@Feature("Deleting")
@DisplayName("Delete entity deleting tests")
@Execution(ExecutionMode.CONCURRENT)
public class DeletingEntityTests extends BaseTest {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Delete entity: removed ")
    @Description("Entity not exist")
    public void testDeleteEntity() {
        // Pre-condition
        Entity entity = new Entity("EntityToDelete",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});

        Response response = EntitiesSteps.createEntity(entity);
        Integer entityId = EntitiesHelper.getEntityIdFormResponse(response);
        // Act
        EntitiesSteps.deleteEntity(entityId);

        // Assert
        Response responseAfterDelete = EntitiesSteps.getEntity(entityId);
        assertEquals(500, responseAfterDelete.statusCode());
        assertTrue(responseAfterDelete.body().asString().contains("no rows in result set"));
    }

}