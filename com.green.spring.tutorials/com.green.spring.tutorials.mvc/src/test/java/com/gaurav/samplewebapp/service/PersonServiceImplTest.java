package com.gaurav.samplewebapp.service;

import com.green.samplewebapp.domain.Person;
import com.green.samplewebapp.repository.PersonRepository;
import com.green.samplewebapp.service.PersonServiceImpl;

import java.util.Arrays;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

/**
 *
 * @author gaurav
 */
@RunWith(MockitoJUnitRunner.class)
public class PersonServiceImplTest {
    
    @Mock private PersonRepository personRepository;
    @InjectMocks private PersonServiceImpl personService;
    
    @Test
    public void itShouldSavePerson(){
        Person person = new Person();
        person.setAge(1);
        person.setFirstName("Peter");
        person.setLastName("Kirk");
        
        personService.save(person);
        
        verify(personRepository).save(person);
    }
    
    @Test
    public void itShouldDeletePerson(){
        personService.delete(1l);
        verify(personRepository).delete(1l);
    }
    
    @Test
    public void itShouldFindPersonById(){
        Person person = new Person();
        person.setAge(23);
        person.setFirstName("Peter");
        person.setLastName("Kirk");
        
        when(personRepository.findOne(1l)).thenReturn(person);
        
        Person personFromService  = personService.findPersonById(1l);
        
        verify(personRepository).findOne(1l);
        
         Assert.assertEquals(personFromService, person);
    }
    
    @Test
    public void itShouldFindAllPersons(){
        Person person = new Person();
        person.setAge(23);
        person.setFirstName("Peter");
        person.setLastName("Kirk");
        
        Person personAnother = new Person();
        personAnother.setAge(23);
        personAnother.setFirstName("Peter");
        personAnother.setLastName("Kirk");
        
        when(personRepository.findAll()).thenReturn(Arrays.asList(person,personAnother));
        
        List<Person> personsFromService  = personService.finAllPerson();
        
        verify(personRepository).findAll();
        
        Assert.assertTrue(personsFromService.size() == 2);
    }
    
    @Test
    public void itShouldUpdatePerson(){
        Person person = new Person();
        person.setId(1l);
        person.setAge(23);
        person.setFirstName("Peter");
        person.setLastName("Kirk");
        
        Person personStubFromDb = new Person();
        personStubFromDb.setId(1l);
        personStubFromDb.setAge(43);
        personStubFromDb.setFirstName("Val");
        personStubFromDb.setLastName("Kilmer");
        
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        

        
        when(personRepository.findOne(1l)).thenReturn(personStubFromDb);
        
        personService.update(person);
        
        verify(personRepository).save(argument.capture());
        
        Assert.assertEquals("Peter", argument.getValue().getFirstName());
        Assert.assertEquals("Kirk", argument.getValue().getLastName());
        Assert.assertEquals(23, argument.getValue().getAge().intValue());
        Assert.assertEquals(1l, argument.getValue().getId().longValue());

    }
    
    
}
