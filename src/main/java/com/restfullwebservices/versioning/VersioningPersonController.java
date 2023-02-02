package com.restfullwebservices.versioning;

import lombok.Data;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class VersioningPersonController {

    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionPerson(){
        return new PersonV1("Yasin Kh");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionPerson(){
        return new PersonV2(new Name("Yasin","Khorasani"));
    }
}
