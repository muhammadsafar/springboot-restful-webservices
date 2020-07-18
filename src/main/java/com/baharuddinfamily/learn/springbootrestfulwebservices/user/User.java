package com.baharuddinfamily.learn.springbootrestfulwebservices.user;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details abuot the user. ")
@Entity
public class User {

	@Id
	@GeneratedValue
	private Integer id;

	@Size(min = 3, message = "name should have atleast 3 characters!")
	@ApiModelProperty(notes = "name should have atleast 3 characters!")
	private String name;

	@Past(message = "birth date must a past")
	@ApiModelProperty(notes = "birthdate should be past")
	private Date bdate;

	@OneToMany(mappedBy = "user")
	private List<Post> posts;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String name, Date bdate) {
		super();
		this.id = id;
		this.name = name;
		this.bdate = bdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBdate() {
		return bdate;
	}

	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", bdate=" + bdate + "]";
	}

}
