package com.green.spring.beans.repository;

import java.util.List;

import com.green.spring.beans.entity.Person;

public interface PersonRepository {

	public void saveUpdate(Person person);
	public void delete(Person person);
	public Person findPersonById(Long id);
	public Person findPersonReferenceById(Long id);
	public List<Person> findAllPerson();
	public Person findPersonByName(String name);
	
}
