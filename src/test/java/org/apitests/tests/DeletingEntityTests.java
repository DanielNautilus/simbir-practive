package org.apitests.tests;

import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.apitests.steps.EntitiesSteps;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNull;

public class DeletingEntityTests extends BaseTest {
    @Test
    public void testDeleteEntity() {
        // Pre-condition: Создаем сущность
        Entity entity = new Entity("EntityToDelete",
                true,
                new Addition(
                        "info",
                        22
                ),
                new Integer[]{42, 87, 15});

        Integer entityId = EntitiesSteps.createEntity(entity);

        // Act: Удаляем сущность
        EntitiesSteps.deleteEntity(entityId);

        // Assert: Проверяем, что сущность удалена
        Entity deletedEntity = EntitiesSteps.getEntity(entityId);
        assertNull(deletedEntity);

        // Можно также проверить, что сервер вернул 404 (или другой код, указывающий на отсутствие сущности)
        // assertEquals(404, deletedEntity.getStatusCode());
    }
}