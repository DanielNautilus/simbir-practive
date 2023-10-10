package org.apitests.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apitests.asserts.EntityAsserts;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.steps.EntitiesSteps;
import org.helpers.EntitiesHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
@Epic("Entities Management")
@Feature("Updated")
@DisplayName("Updating entity tests")
@Execution(ExecutionMode.CONCURRENT)
public class UpdatingEntityTests extends BaseTest {
    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Updating single entity")
    @Description("Updating confirm")
    public void testUpdateEntity() {
        // Pre-condition
        Entity entity = new Entity("EntityToUpdate",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});

        Integer entityId = EntitiesHelper.getEntityIdFormResponse(EntitiesSteps.createEntity(entity));

        // act
        Entity updatedEntity = new Entity("UpdatedEntity",
                true,
                new Addition(
                        "new_info",
                        42
                ),
                new Integer[]{12, 34, 56});

        Response response = EntitiesSteps.updateEntity(entityId, updatedEntity);
        Entity updatedEntityFromServer = EntitiesHelper.getEntityFormResponse(EntitiesSteps.getEntity(entityId));

        //Assert
        EntityAsserts.assertEntityEquivalent(updatedEntity, updatedEntityFromServer);
        assertEquals(204,response.statusCode());

        //post-action
        EntitiesSteps.deleteEntity(entityId);
    }
}