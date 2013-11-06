package com.green.samplewebapp.service;

import com.green.samplewebapp.domain.Person;
import com.green.samplewebapp.repository.PersonRepository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The service class responsible for handling any business logic(transactional) for Person and delegating
 * persistence to repository
 * 
 * @author gaurav
 */
@Service("personService")
public class PersonServiceImpl implements PersonService{
    @Autowired private PersonRepository personRepository;

    /**
     * Handles transactional save
     * @param person 
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void save(Person person) {
        personRepository.save(person);
    }

    /**
     * Handles transactional delete
     * @param id 
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        personRepository.delete(id);
    }

    /**
     * Listing all Persons
     * 
     * @return List of all Persons
     */
    @Override
    public List<Person> finAllPerson() {
        return personRepository.findAll();
    }

    /**
     * Finding a person by id
     * 
     * @param id
     * @return a Person
     */
    @Override
    public Person findPersonById(Long id) {
        return personRepository.findOne(id);
    }

    /**
     * Handles transactional update
     * @param id 
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(Person person) {
        Person personFormDb = personRepository.findOne(person.getId());
        personFormDb.setAge(person.getAge());
        personFormDb.setFirstName(person.getFirstName());
        personFormDb.setLastName(person.getLastName());
        personRepository.save(personFormDb);
    }
    
    
}
