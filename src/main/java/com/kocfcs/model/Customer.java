package com.kocfcs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Customer {

	@Id
	@Column(name = "ID_NUMBER")
	private String idNumber;
	
	@Column(name = "MSISDN")
	private String msisdn;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "SURNAME")
	private String surname;
	
	@Column(name = "CREDIT")
	private int credit;
	
}
