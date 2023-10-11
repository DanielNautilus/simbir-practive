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
@Feature("Creation")
@DisplayName("Create entity creation tests")
@Execution(ExecutionMode.CONCURRENT)
public class CreationEntityTests extends BaseTest {

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Create entity: added ")
    @Description("Set all fields create entity")
    public void testCreateEntityIdAutoincrement() {
        //Pre-condition
        Entity expectedEntity = new Entity(
                "name1",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15}
        );

        // Act
        Response response = EntitiesSteps.createEntity(expectedEntity);
        Integer actualEntityId = EntitiesHelper.getEntityIdFormResponse(response);
        Entity actualEntity = EntitiesHelper.getEntityFormResponse(EntitiesSteps.getEntity(actualEntityId));

        // Assert
        assertEquals(200, response.statusCode());
        EntityAsserts.assertEntityEquivalent(expectedEntity, actualEntity);

        // Post-action
        EntitiesSteps.deleteEntity(actualEntityId);
    }

}
