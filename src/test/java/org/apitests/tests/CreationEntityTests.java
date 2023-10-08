package org.apitests.tests;

import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.steps.EntitiesSteps;
import org.helpers.EntitiesHelper;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreationEntityTests extends BaseTest {

    @Test
    public void testCreateEntityIdAutoincrement() {
        Integer expectedEntityId = EntitiesHelper.getMaxEntityId(EntitiesSteps.getAllEntities(null)) + 1;
        var expectedEntity = new Entity(
                "name1",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15}
        );
        var actualEntityId = EntitiesSteps.createEntity(expectedEntity);
        assertEquals(expectedEntityId, actualEntityId);

        //post-action
        EntitiesSteps.deleteEntity(actualEntityId);
    }
}
