package org.apitests.tests;

import org.apitests.asserts.EntityAsserts;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.steps.EntitiesSteps;
import org.helpers.EntitiesHelper;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GettingEntityTests extends BaseTest{

    @Test
    public void testGetSingleEntity(){
        //pre-condition
        List<Entity> entityList = EntitiesSteps.getAllEntities(null);
        Integer expectedEntityId = EntitiesHelper.getMaxEntityId(entityList) + 1;
        Integer expectedAdditionId = EntitiesHelper.getMaxAdditionIdFromEntities(entityList) + 1;
        var expectedEntity = new Entity(
                "name1",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15}
        );
        Integer actualEntityId = EntitiesSteps.createEntity(expectedEntity);

        //act
        Entity actualEntity = EntitiesSteps.getEntity(actualEntityId);
        EntityAsserts.assertEntityEquivalent(expectedEntity,expectedEntityId,expectedAdditionId,actualEntity);

        //post action
        EntitiesSteps.deleteEntity(actualEntityId);
    }
}
