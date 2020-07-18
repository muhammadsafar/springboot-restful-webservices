package com.baharuddinfamily.learn.springbootrestfulwebservices.helloworld;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

    @GetMapping(path = "/")
    public String helloWorld() {
        return ("<h1>Hello World. its works<h1>");
    }

    @GetMapping(path = "/helloworldbean")
    public HelloWorldBean helloWorldBean() {
        return new HelloWorldBean("its a json value");
    }

    @GetMapping(path = "/helloworld/path/{name}")
    public HelloWorldBean helloWorldPath(@PathVariable("name") String name) {
        return new HelloWorldBean("its a json value with params: " + name);
    }

    // versi 1
    public String helloWorldPathinternationalized(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("good.morning.message", null, locale);
    }

    // versi 2
    @GetMapping(path = "/helloworld/internationalized")
    public String helloWorldPathinternationalizedv2() {
        return messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale());
    }

}
