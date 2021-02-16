package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="division")
public class Division {
	
	@Id
	@Column
	private int id;
	@Column
	private String name;

}
