package com.example.pagingwithoutjpa.model;

public class Users {

	private String name;
	private String lastname;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Users(String name, String lastname) {
		super();
		this.name = name;
		this.lastname = lastname;
	}

	@Override
	public String toString() {
		return "Users [name=" + name + ", lastname=" + lastname + "]";
	}

}
