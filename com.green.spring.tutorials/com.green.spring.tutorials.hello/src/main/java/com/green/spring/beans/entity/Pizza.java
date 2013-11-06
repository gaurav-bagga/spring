package com.green.spring.beans.entity;

/**
 * The pizza pojo
 * 
 * @author gaurav
 *
 */
public class Pizza {

	public static final String PEPPY_PANEER = "Peppy Paneer";
	
	private String name;
	
	public Pizza(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
