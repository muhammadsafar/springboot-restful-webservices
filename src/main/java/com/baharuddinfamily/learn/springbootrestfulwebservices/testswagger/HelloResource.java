package com.baharuddinfamily.learn.springbootrestfulwebservices.testswagger;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/rest/hello")
@Api(value = "HelloWorld Resource", description = "Show the hello world messages")
public class HelloResource {

	@ApiOperation(value = "Return hello world")
	@ApiResponses(value = { @ApiResponse(code = 100, message = "100 is the message"),
			@ApiResponse(code = 200, message = "Seccuessful Hello")

	})

	@GetMapping
	public String hello() {
		return "Hello u";
	}

	@ApiOperation(value = "Return hello world")
	@PostMapping
	public String helloAdd(@RequestBody final String hello) {
		return hello;
	}

	@ApiOperation(value = "Return hello world")
	@PutMapping
	public String helloPut(@RequestBody final String hello) {
		return hello;
	}

}
