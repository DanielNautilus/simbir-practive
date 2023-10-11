package org.apitests.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Addition implements IdentifiableModel {
    @JsonProperty("id")
    @Getter @Setter
    Integer id;
    @JsonProperty("additional_info")
    @Getter @Setter
    String additionalInfo;
    @JsonProperty("additional_number")
    @Getter @Setter
    Integer additionalNumber;
    public Addition(){}
    public Addition(String additionalInfo, Integer additionalNumber) {
        this.additionalInfo = additionalInfo;
        this.additionalNumber = additionalNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Addition addition = (Addition) o;
        return Objects.equals(additionalInfo, addition.additionalInfo) &&
                Objects.equals(additionalNumber, addition.additionalNumber);
    }

}
