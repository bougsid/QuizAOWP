package com.bougsid.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class UserCategory {
	@Id
	private String id;
	private String name;
	private List<String> users;

	public UserCategory() {
		super();
		this.users = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}

	public void addUser(String userId) {
		if (this.users != null)
			this.users.add(userId);
	}
}
