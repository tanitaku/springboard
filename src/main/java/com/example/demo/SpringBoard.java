package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "SpringBoard")
public class SpringBoard {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private int id;

	@Column
	private Date createDate;
	
	@Column(nullable = false)
	@NotEmpty
	private String title;
	
	@Column(nullable = false)
	@NotEmpty
	private String content;
	
	@Column
	private String createUser;
	
	@Column
	private int division;
	

}
