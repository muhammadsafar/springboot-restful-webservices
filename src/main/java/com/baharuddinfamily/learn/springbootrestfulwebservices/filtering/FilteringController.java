package com.baharuddinfamily.learn.springbootrestfulwebservices.filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController {

    @GetMapping("/filtering")
    public MappingJacksonValue retrieve() {

        SomeBean data = new SomeBean("field1", "field2", "field3");
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterNamesCode", filter);

        MappingJacksonValue jacksonValue = new MappingJacksonValue(data);
        jacksonValue.setFilters(filters);

        return jacksonValue;
    }

    @GetMapping("/filtering-list")
    public MappingJacksonValue retrieveList() {

        List<SomeBean> data = Arrays.asList(new SomeBean("value1", "value2", "value3"),
                new SomeBean("value4", "value5", "value6"));
        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2", "field3");

        FilterProvider filters = new SimpleFilterProvider().addFilter("FilterNamesCode", filter);

        MappingJacksonValue jacksonValue = new MappingJacksonValue(data);
        jacksonValue.setFilters(filters);

        return jacksonValue;

    }

}
