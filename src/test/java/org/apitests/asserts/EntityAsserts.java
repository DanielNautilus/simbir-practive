package org.apitests.asserts;

import io.qameta.allure.Step;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apitests.models.Addition;
import org.apitests.models.Entity;
import org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.recursive.comparison.RecursiveComparisonConfiguration.builder;
import org.assertj.core.api.Assertions;
public class EntityAsserts {
    private static final String[] ignoringFields = {"id", "addition.id", "entity.id"};
    private static final RecursiveComparisonConfiguration configuration = builder()
            .withIgnoredFields(ignoringFields)
            .build();

    @Step("Assert entities Equivalent")
    public static void assertEntityEquivalent(Entity expectedEntity, Entity actualEntity) {
        assertThat(expectedEntity)
                .usingRecursiveComparison(configuration)
                .isEqualTo(actualEntity);
    }



}
