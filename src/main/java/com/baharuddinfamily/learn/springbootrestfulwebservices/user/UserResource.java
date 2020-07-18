package com.baharuddinfamily.learn.springbootrestfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.baharuddinfamily.learn.springbootrestfulwebservices.exception.UserNotFoundException;

@RestController
public class UserResource {

	@Autowired
	private UserDao userDao;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<User> users() {
		return userDao.findAll();
	}

	public User getOne(@PathVariable int id) {
		User user = userDao.findOne(id);
		if (user == null) {
			System.out.println("works ");
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}

	// using hateoas
	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	public Resource<User> getOneUsingHateoas(@PathVariable int id) {
		User user = userDao.findOne(id);
		if (user == null) {
			System.out.println("works ");
			throw new UserNotFoundException("id - " + id);
		}
		// HATEOAS
		// Hypermedia As The Engine Of Application State

		Resource<User> resource = new Resource<User>(user);

		// import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).users());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	// save without response entity
	public void save(@Valid @RequestBody User user) {
		userDao.save(user);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUsingResponseEntity(@Valid @RequestBody User user) {
		User savedUser = userDao.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id) {

		User user = userDao.deleteById(id);

		if (user == null) {
			throw new UserNotFoundException("id - " + id);
		}
	}
}
