package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;


@Entity
@Table(name = "user")
public class User {
	@Id
	@Column
	private String username;
	public String getEncodingPassword() {
		return encodingPassword;
	}

	public void setEncodingPassword(String encodingPassword) {
		this.encodingPassword = encodingPassword;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column
	private String encodingPassword;
	
	public String getUsername() {
		return username;
	}

}
