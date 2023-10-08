package org.apitests.tests;

import org.apitests.asserts.EntityAsserts;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.models.EntitySearchTerm;
import org.apitests.steps.EntitiesSteps;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GettingEntitiesAllTests extends BaseTest{
    @Test
    public void testGettingAllVerifiedEntities() {
        //pre-condition
        Entity entity1 = new Entity("Entity1",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});
        Entity entity2 = new Entity("Entity2",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});

        Integer id1 = EntitiesSteps.createEntity(entity1);
        Integer id2 = EntitiesSteps.createEntity(entity2);

        //act
        EntitySearchTerm searchTerm = new EntitySearchTerm();
        searchTerm.setVerified(true);

        List<Entity> allEntities = EntitiesSteps.getAllEntities(searchTerm);

        assertNotNull(allEntities);
        assertTrue(allEntities.size() >= 2);

        boolean entity1Found = false;
        boolean entity2Found = false;

        for (Entity entity : allEntities) {
            if (entity.getId().equals(id1)) {
                entity1Found = true;
                EntityAsserts.assertEntityEquivalent(entity1, id1, id1, entity);
            }
            if (entity.getId().equals(id2)) {
                entity2Found = true;
                EntityAsserts.assertEntityEquivalent(entity2, id2, id2, entity);
            }
        }

        assertTrue(entity1Found);
        assertTrue(entity2Found);

        EntitiesSteps.deleteEntity(id1);
        EntitiesSteps.deleteEntity(id2);
    }
}
