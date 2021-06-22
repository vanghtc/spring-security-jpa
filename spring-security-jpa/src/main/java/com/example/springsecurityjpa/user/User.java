package com.example.springsecurityjpa.user;

import lombok.Data;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name="User")
@Entity
public class User {
	
	@Id
	@GeneratedValue
	private long id;
	
	@Column(nullable=false, unique = true)
	private String userName;
	 
	private String password;
	
	
}
