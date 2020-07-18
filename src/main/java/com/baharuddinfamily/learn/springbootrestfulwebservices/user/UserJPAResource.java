package com.baharuddinfamily.learn.springbootrestfulwebservices.user;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
@RequestMapping("/jpa")
public class UserJPAResource {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private PostRepository postRepository;

	@RequestMapping(path = "/users", method = RequestMethod.GET)
	public List<User> users() {
		return userRepo.findAll();
	}

	public Optional<User> getOne(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			System.out.println("works ");
			throw new UserNotFoundException("id - " + id);
		}
		return user;
	}

	// using hateoas
	@RequestMapping(path = "/users/{id}", method = RequestMethod.GET)
	public Resource<User> getOneUsingHateoas(@PathVariable int id) {
		Optional<User> user = userRepo.findById(id);
		if (!user.isPresent()) {
			System.out.println("works ");
			throw new UserNotFoundException("id - " + id);
		}
		// HATEOAS
		// Hypermedia As The Engine Of Application State

		Resource<User> resource = new Resource<User>(user.get());

		// import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).users());
		resource.add(linkTo.withRel("all-users"));
		return resource;
	}

	// save without response entity
	public void save(@Valid @RequestBody User user) {
		userRepo.save(user);
	}

	@PostMapping("/users")
	public ResponseEntity<Object> saveUsingResponseEntity(@Valid @RequestBody User user) {
		User savedUser = userRepo.save(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/users/{id}")
	public void delete(@PathVariable int id) {
		userRepo.deleteById(id);
	}

	@RequestMapping(path = "/users/{id}/posts", method = RequestMethod.GET)
	public List<Post> retrieveAllUser(@PathVariable int id) {
		Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent()) {
			System.out.println("here works");
			throw new UserNotFoundException("id - " + id);
		}

		if (userOptional.get().getPosts().isEmpty()) {
			throw new UserNotFoundException("Posts not found for user - " + id);
		}
		return userOptional.get().getPosts();

	}

	@PostMapping("/users/{id}/posts")
	public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post) {
		Optional<User> userOptional = userRepo.findById(id);

		if (!userOptional.isPresent()) {
			System.out.println("works ");
			throw new UserNotFoundException("id - " + id);
		}

		User user = userOptional.get();

		post.setUser(user);
		postRepository.save(post);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(post.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}
}
