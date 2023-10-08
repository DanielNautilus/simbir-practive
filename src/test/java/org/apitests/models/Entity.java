package org.apitests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
}
