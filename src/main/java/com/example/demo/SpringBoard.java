package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

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
	
	@Column
	private String title;
	
	@Column
	private String content;
	
	@Column
	private String createUser;
	

}
