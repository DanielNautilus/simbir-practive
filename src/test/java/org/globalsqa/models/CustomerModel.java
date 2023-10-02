package org.globalsqa.models;

import lombok.Getter;
import lombok.Setter;

public class CustomerModel {
    @Getter @Setter
    private Integer id;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    private String postCode;

    public CustomerModel(String firstName, String lastName, String postCode, Integer id) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postCode = postCode;
        this.id = id;
    }
}
