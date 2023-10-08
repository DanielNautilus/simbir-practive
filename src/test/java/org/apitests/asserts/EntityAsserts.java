package org.apitests.asserts;

import io.qameta.allure.Step;
import org.apitests.models.Addition;
import org.apitests.models.Entity;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class EntityAsserts {

    @Step("Assert entities Equivalent")
    public static void assertEntityEquivalent(Entity expectedEntity, Entity actualEntity ){
        assertEquals(expectedEntity.getTitle(), actualEntity.getTitle());
        assertEquals(expectedEntity.getVerified(), actualEntity.getVerified());
        assertEquals(expectedEntity.getVerified(), actualEntity.getVerified());
        assertAdditionalEquivalent(expectedEntity.getAddition(),actualEntity.getAddition());
        assertArrayEquals(expectedEntity.getImportantNumbers(), actualEntity.getImportantNumbers());
    }
    @Step("Assert entities additional Equivalent")
    private static void assertAdditionalEquivalent(Addition expectedAddition, Addition actualAddition){
        assertEquals(expectedAddition.getAdditionalInfo(), actualAddition.getAdditionalInfo());
        assertEquals(expectedAddition.getAdditionalNumber(), actualAddition.getAdditionalNumber());
    }
}
