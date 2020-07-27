package com.baharuddinfamily.learn.springbootrestfulwebservices.testswagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;

@RestController
@RequestMapping("/rest/user")
@Api(value = "User Resource REST endpoint", description = "Shows the user data")
public class UserResourceSwagger {

	@GetMapping
	public List<UserSwagger> getList() {

		return Arrays.asList(new UserSwagger("ummi", 40000L), new UserSwagger("Abi", 10000L)

		);
	}

	@GetMapping("/{username}")
	public UserSwagger getUser(@PathVariable("username") String uname) {

		return new UserSwagger(uname, 1000L);
	}

	public class UserSwagger {
	
		@ApiModelProperty(notes = "name of the users")
		private String userName;

		@ApiModelProperty(notes = "salary of the users")
		private Long salary;

		public UserSwagger(String userName, Long salary) {
			super();
			this.userName = userName;
			this.salary = salary;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public Long getSalary() {
			return salary;
		}

		public void setSalary(Long salary) {
			this.salary = salary;
		}

	}

}
