package org.apitests.models;

import lombok.Getter;
import lombok.Setter;

public class EntitySearchTerm {
    @Getter @Setter
    Boolean verified;
    @Getter @Setter
    String title;
    @Getter @Setter
    Integer page;
    @Getter @Setter
    Integer perPage;
}
