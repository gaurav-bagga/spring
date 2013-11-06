package com.green.spring.beans.jpa.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.green.spring.beans.entity.Person;
import com.green.spring.beans.repository.PersonRepository;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:jpa.xml"})
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseTearDown(value="resetData.xml",type = DatabaseOperation.DELETE_ALL)
public class PersonRepositoryJPAImplTest {
	
	@Autowired @Qualifier("PersonRepositoryJPA") private PersonRepository personRepository;
	
	@Test
	public void itShouldSaveNewPerson(){
		Person person = new Person();
		person.setName("Peter");
		personRepository.saveUpdate(person);
		Assert.assertNotNull(person.getId());
	}
	
	@Test
    @DatabaseSetup("sampleData.xml")
    public void itShouldFindAllPersons() throws Exception {
        List<Person> personList = this.personRepository.findAllPerson();
        Assert.assertTrue(personList.size() == 2);
        Assert.assertFalse(personList.isEmpty());

    }

    @Test
    @DatabaseSetup("sampleData.xml")
    @ExpectedDatabase("deleteExpectedData.xml")
    public void itShouldDeletePersonById() throws Exception {
    	Person person = this.personRepository.findPersonReferenceById(1l);
        this.personRepository.delete(person);
    }
    
    
    @Test
    @DatabaseSetup("sampleData.xml")
    @ExpectedDatabase("updateExpectedData.xml")
    public void itShouldUpdatePersonById() throws Exception {
        Person person = this.personRepository.findPersonById(2l);
        person.setName("Val");
        this.personRepository.saveUpdate(person);
    }

}
