package com.restfullwebservices.versioning;

import lombok.Data;

@Data
public class PersonV2 {

    private Name name;
    public PersonV2(Name name) {
        super();
        this.name= name;
    }
}
