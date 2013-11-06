package com.gaurav.samplewebapp.repository;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.green.samplewebapp.domain.Person;
import com.green.samplewebapp.repository.PersonRepository;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

/**
 *
 * @author gaurav
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
@ActiveProfiles("dev")
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, DbUnitTestExecutionListener.class})
@DatabaseTearDown(value="resetData.xml",type = DatabaseOperation.TRUNCATE_TABLE)
public class PersonRepositoryTest {

    @Autowired
    PersonRepository personRepository;

    @Test
    public void itShouldCreateANewEntity() {
        Person person = new Person();
        person.setFirstName("Jim");
        person.setLastName("Kirk");
        person.setAge(30);
        personRepository.save(person);
        Assert.assertNotNull(person.getId());
    }

    @Test
    @DatabaseSetup("sampleData.xml")
    public void itShouldFindAllPersons() throws Exception {
        List<Person> personList = this.personRepository.findAll();
        Assert.assertTrue(personList.size() == 2);
        Assert.assertFalse(personList.isEmpty());

    }

    @Test
    @DatabaseSetup("sampleData.xml")
    @ExpectedDatabase("deleteExpectedData.xml")
    public void itShouldDeletePersonById() throws Exception {
        this.personRepository.delete(1l);
    }
    
    
    @Test
    @DatabaseSetup("sampleData.xml")
    @ExpectedDatabase("updateExpectedData.xml")
    public void itShouldUpdatePersonById() throws Exception {
        Person person = this.personRepository.findOne(2l);
        person.setFirstName("Val");
        person.setLastName("Kilmer");
        person.setAge(50);
        this.personRepository.save(person);
    }
}
