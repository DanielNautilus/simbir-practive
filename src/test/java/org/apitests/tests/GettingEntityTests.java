package org.apitests.tests;

import io.qameta.allure.*;
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

@Epic("Entities Management")
@Feature("Getting")
@DisplayName("Getting entity tests")
@Execution(ExecutionMode.CONCURRENT)
public class GettingEntityTests extends BaseTest{

    @Test
    @Severity(SeverityLevel.CRITICAL)
    @DisplayName("Getting single entity")
    @Description("Entity exist")
    public void testGetSingleEntity(){
        //pre-condition
        Entity expectedEntity = new Entity(
                "name1",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15}
        );
        Integer actualEntityId = EntitiesHelper.getEntityIdFormResponse(EntitiesSteps.createEntity(expectedEntity));

        // Act
        Response response = EntitiesSteps.getEntity(actualEntityId);
        Entity actualEntity = EntitiesHelper.getEntityFormResponse(response);

        //Assert
        EntityAsserts.assertEntityEquivalent(expectedEntity, actualEntity);

        //post action
        EntitiesSteps.deleteEntity(actualEntityId);
    }
}
