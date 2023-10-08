package org.apitests.asserts;

import io.qameta.allure.Step;
import org.apitests.models.Addition;
import org.apitests.models.Entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityAsserts {

    @Step("Assert entities Equivalent")
    public static void assertEntityEquivalent(Entity expectedEntity, Integer expectedEntityId, Integer expectedAdditionId,Entity actualEntity ){
        assertEquals(expectedEntityId, actualEntity.getId());
        assertEquals(expectedEntity.getTitle(), actualEntity.getTitle());
        assertEquals(expectedEntity.getVerified(), actualEntity.getVerified());
        assertEquals(expectedEntity.getVerified(), actualEntity.getVerified());
        assertAdditionalEquivalent(expectedEntity.getAddition(),expectedAdditionId,actualEntity.getAddition());
        assertArrayEquals(expectedEntity.getImportantNumbers(), actualEntity.getImportantNumbers());
    }
    @Step("Assert entities additional Equivalent")
    private static void assertAdditionalEquivalent(Addition expectedAddition, Integer expectedAdditionId, Addition actualAddition){
        assertEquals(expectedAddition.getAdditionalInfo(), actualAddition.getAdditionalInfo());
        assertEquals(expectedAddition.getAdditionalNumber(), actualAddition.getAdditionalNumber());
        assertEquals(expectedAdditionId,actualAddition.getId());
    }
}
