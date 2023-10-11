package org.apitests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Entity implements IdentifiableModel {
    @JsonProperty("id")
    @Getter @Setter
    Integer id;
    @JsonProperty("title")
    @Getter @Setter
    String title;
    @JsonProperty("verified")
    @Getter @Setter
    Boolean verified;
    @JsonProperty("addition")
    @Getter @Setter
    Addition addition;
    @JsonProperty("important_numbers")
    @Getter @Setter
    Integer[] importantNumbers;
    public Entity() {
    }

    public Entity(String title,Boolean verified,Addition addition,Integer[] importantNumbers){
        this.title = title;
        this.verified = verified;
        this.addition = addition;
        this.importantNumbers = importantNumbers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entity entity = (Entity) o;
        return Objects.equals(title, entity.title) &&
                Objects.equals(verified, entity.verified) &&
                Objects.equals(addition, entity.addition) &&
                Arrays.equals(importantNumbers, entity.importantNumbers);
    }

}
