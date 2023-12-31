package org.apitests.tests;
import java.util.List;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.restassured.response.Response;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.models.EntitySearchTerm;
import org.apitests.steps.EntitiesSteps;
import org.helpers.EntitiesHelper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItems;
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
        Entity firstEntity = new Entity("Entity12",
                true,
                new Addition(
                        "info1",
                        42
                ),
                new Integer[]{41, 87, 15});
        Entity secondEntity = new Entity("Entity21",
                true,
                new Addition(
                        "info2",
                        32
                ),
                new Integer[]{42, 87, 25});
        Integer firstEntityId = EntitiesHelper.getEntityIdFormResponse(EntitiesSteps.createEntity(firstEntity));
        Integer secondEntityId = EntitiesHelper.getEntityIdFormResponse(EntitiesSteps.createEntity(secondEntity));

        // Act
        EntitySearchTerm searchTerm = new EntitySearchTerm();
        searchTerm.setVerified(true);
        Response response = EntitiesSteps.getAllEntities(searchTerm);
        List<Entity> allEntities = EntitiesHelper.getAllEntitiesFormResponse(response);

        // Assert
        assertThat(allEntities, hasItems(firstEntity, secondEntity));
        assertEquals(200, response.statusCode());
        assertNotNull(allEntities);
        assertTrue(allEntities.size() >= 2);

        // Post-action: Удаляем созданные сущности после теста
        EntitiesSteps.deleteEntity(firstEntityId);
        EntitiesSteps.deleteEntity(secondEntityId);
    }
}
