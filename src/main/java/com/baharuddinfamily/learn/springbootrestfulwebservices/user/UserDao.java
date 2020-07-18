package com.baharuddinfamily.learn.springbootrestfulwebservices.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UserDao {

	private static int count = 3;

	private static List<User> users = new ArrayList<>();

	static {
		users.add(new User(1, "baharuddin", new Date()));
		users.add(new User(2, "ramlah", new Date()));
		users.add(new User(3, "muhammad", new Date()));
	}

	public List<User> findAll() {

		return users;
	}

	public User save(User user) {
		if (user.getId() == null) {
			user.setId(++count);
		}
		users.add(user);
		return user;
	}

	public User findOne(int id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}

		return null;

	}

	public User deleteById(int id) {

		Iterator<User> iter = users.iterator();

		while (iter.hasNext()) {
			User user = iter.next();
			if (user.getId() == id) {
				iter.remove();
				return user;
			}
		}

		return null;
	}

}
