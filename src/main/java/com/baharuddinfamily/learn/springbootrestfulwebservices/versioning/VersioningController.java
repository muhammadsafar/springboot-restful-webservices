package com.baharuddinfamily.learn.springbootrestfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningController {

    @GetMapping("/person1")
    public PersonV1 personV1() {
        return new PersonV1("muhammad safar baharuddin");
    }

    @GetMapping("/person2")
    public PersonV2 personV2() {
        return new PersonV2(new Name("Muhammad", "Safar baharuddin"));
    }

    // USING PARAM =======HEADER=================
    @GetMapping(value = "/person/param", params = "version=1")
    public PersonV1 paramV1() {
        return new PersonV1("muhammad safar baharuddin");
    }

    @GetMapping(value = "/person/param", params = "version=2")
    public PersonV2 paramV2() {
        return new PersonV2(new Name("Muhammad", "Safar baharuddin"));
    }

    //HEADER => header key name  -> optional  
    @GetMapping(value = "/person/header", headers = "KEY-PERSON=v1")
    public PersonV1 headerV1() {
        return new PersonV1("muhammad safar baharuddin");
    }

    @GetMapping(value = "/person/header", headers = "KEY-PERSON=v2")
    public PersonV2 headerV2() {
        return new PersonV2(new Name("Muhammad", "Safar baharuddin"));
    }

    //PRODUCE => Header-> Accept
    @GetMapping(value = "/person/produces", produces = "application/v1+json")
    public PersonV1 produceV1() {
        return new PersonV1("muhammad safar baharuddin");
    }

    @GetMapping(value = "/person/produces", produces = "application/v2+json")
    public PersonV2 produceV2() {
        return new PersonV2(new Name("Muhammad", "Safar baharuddin"));
    }

}
