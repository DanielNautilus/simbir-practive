package org.apitests.tests;

import org.apitests.asserts.EntityAsserts;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.steps.EntitiesSteps;
import org.junit.jupiter.api.Test;

public class UpdatingEntityTests extends BaseTest {
    @Test
    public void testUpdateEntity() {
        // Pre-condition
        Entity entity = new Entity("EntityToUpdate",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});

        Integer entityId = EntitiesSteps.createEntity(entity);

        // act
        Entity updatedEntity = new Entity("UpdatedEntity",
                true,
                new Addition(
                        "new_info",
                        42
                ),
                new Integer[]{12, 34, 56});

        EntitiesSteps.updateEntity(entityId, updatedEntity);
        Entity updatedEntityFromServer = EntitiesSteps.getEntity(entityId);

        EntityAsserts.assertEntityEquivalent(updatedEntity, entityId, entityId, updatedEntityFromServer);

        //post-action
        EntitiesSteps.deleteEntity(entityId);
    }
}