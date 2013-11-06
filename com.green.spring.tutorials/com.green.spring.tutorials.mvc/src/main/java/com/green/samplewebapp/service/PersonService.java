package com.green.samplewebapp.service;

import com.green.samplewebapp.domain.Person;

import java.util.List;

/**
 *
 * @author gaurav
 */
public interface PersonService {
 
    public void save(Person person);
    public void delete(Long id);
    public List<Person> finAllPerson();
    public Person findPersonById(Long id);
    public void update(Person person);
}
