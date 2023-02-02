package com.restfullwebservices.versioning;

import lombok.Data;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class VersioningPersonController {

/////////////////////////versioning REST ARI;URI -Twitter
    @GetMapping("/v1/person")
    public PersonV1 getFirstVersionPerson(){
        return new PersonV1("Yasin Kh");
    }

    @GetMapping("/v2/person")
    public PersonV2 getSecondVersionPerson(){
        return new PersonV2(new Name("Yasin","Khorasani"));
    }
////////////////////////////versioning REST ARI; Request Param -Amazon
    @GetMapping(path="/person", params = "version=1")
    public PersonV1 getFirstVersionPersonRequestParameter(){
        return new PersonV1("Yasin Kh");
    }

    @GetMapping(path="/person", params = "version=2")
    public PersonV2 getSecondVersionPersonRequestParameter(){
        return new PersonV2(new Name("Hasti","barahoei"));
    }
////////////////////////versioning REST ARI,(Custom) Header - Microsoft

    @GetMapping(path = "/person/header",headers = "X-API-VERSION=1")
    public PersonV1 getSFirstVersionPersonRequestHeader(){
        return new PersonV1("Mani Movassagh");
    }
    @GetMapping(path = "/person/header",headers = "X-API-VERSION=2")
    public PersonV2 getSecondVersionPersonRequestHeader(){
        return new PersonV2(new Name("Sahar" , "morattab"));
    }
    ///////////////////////// Accept Header -Github
    @GetMapping(path = "/person/header",produces= "application/vnd.company.app-v1+json")
    public PersonV1 getSFirstVersionPersonAcceptHeader(){
        return new PersonV1("Mani Movassagh");
    }
    @GetMapping(path = "/person/header",produces= "application/vnd.company.app-v2+json")
    public PersonV2 getSecondVersionPersonAcceptHeader(){
        return new PersonV2(new Name("Hasti","barahoei"));
    }
}
