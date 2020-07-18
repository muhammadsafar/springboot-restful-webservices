package com.baharuddinfamily.learn.springbootrestfulwebservices.versioning;

public class Name {

	private String fname;

	private String lname;

	public Name() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Name(String fname, String lname) {
		super();
		this.fname = fname;
		this.lname = lname;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

}
