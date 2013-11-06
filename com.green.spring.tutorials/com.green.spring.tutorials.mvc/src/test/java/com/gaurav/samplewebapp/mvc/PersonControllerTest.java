package com.gaurav.samplewebapp.mvc;

import com.green.samplewebapp.domain.Person;
import com.green.samplewebapp.service.PersonService;

import java.util.Arrays;
import javax.naming.NamingException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.flash;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.hamcrest.Matchers.*;
import org.junit.Assert;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

/**
 *
 * @author gaurav
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {
    "classpath:test-root-context.xml",
    "file:src/main/webapp/WEB-INF/spring/sample-app-servlet.xml",})
@ActiveProfiles("dev")
public class PersonControllerTest {

    @Autowired
    private WebApplicationContext wac;
    @Autowired
    private PersonService personService;
    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws NamingException {
        // setup mock MVC
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).alwaysDo(print()).build();
        reset(personService);
    }

    @Test
    public void itShouldShowEmptyPersonCreateForm() throws Exception {
        //given a request to show create person page
        mockMvc.perform(get("/"))
                //then the module attribute should have Person POJO by attribute key person        
                .andExpect(model().attribute("person", instanceOf(Person.class)))
                .andExpect(view().name("new"))
                //and response shoud be ok
                .andExpect(status().isOk());
    }

    @Test
    public void itShouldCreateValidNewPersonDataAndRedirectToListingPageWithFlashMessage() throws Exception {
        //given a request with params
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        //when a post request is made to /create page
        mockMvc.perform(post("/create")
                .param("firstName", "Peter")
                .param("lastName", "Parker")
                .param("age", "23")).andExpect(status().isMovedTemporarily())
                //then it should redirect to list page
                .andExpect(redirectedUrl("list"))
                .andExpect(model().hasNoErrors())
                //and flash map should contain success message
                .andExpect(flash().attributeExists("sucMsg"));
        //and the pojo passed to save method as properties set as parameter passed
        verify(personService).save(argument.capture());
        
        Assert.assertEquals("Peter", argument.getValue().getFirstName());
        Assert.assertEquals("Parker", argument.getValue().getLastName());
        Assert.assertEquals(23, argument.getValue().getAge().intValue());
    }

    @Test
    public void itShouldNotCreateInValidNewPersonDataAndForwardBackToNewCustomerPageWithErrors() throws Exception {
        //given a request with params
        //when a post request is made to /create page
        //and a field is missing this case lastName  
        mockMvc.perform(post("/create")
                .param("firstName", "Peter")
                .param("lastName", "")
                .param("age", "23")).andExpect(status().isOk())
                //then it should forward back to create page with filled in fields returnred back
                .andExpect(view().name("new"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(2))
                .andExpect(model().attribute("person", hasProperty("firstName", is("Peter"))))
                .andExpect(model().attribute("person", hasProperty("age", is(23))))
                .andExpect(model().attributeHasFieldErrors("person", "lastName"));
        //and save method is never called
        verify(personService,never()).save(Mockito.any(Person.class));

    }
    
    @Test
    public void itShouldShowListingsOfAllPersons() throws Exception {
        //given a list of persons
        Person person = new Person();
        person.setId(1l);
        person.setAge(23);
        person.setFirstName("Peter");
        person.setLastName("Kirk");
        
        Person anotherPerson = new Person();
        anotherPerson.setId(2l);
        anotherPerson.setAge(43);
        anotherPerson.setFirstName("Val");
        anotherPerson.setLastName("Kilmer");
        
        when(personService.finAllPerson()).thenReturn(Arrays.asList(person,anotherPerson));
        //when a get request is made to /list page
        mockMvc.perform(get("/list"))
                .andExpect(status().isOk())
                //then it should forward  to list page with details retrieved form service
                .andExpect(view().name("list"))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("id", is(1L)),
                                hasProperty("firstName", is("Peter")),
                                hasProperty("lastName", is("Kirk")),
                                hasProperty("age", is(23))
                        )
                )))
                .andExpect(model().attribute("personList", hasItem(
                        allOf(
                                hasProperty("id", is(2L)),
                                hasProperty("firstName", is("Val")),
                                hasProperty("lastName", is("Kilmer")),
                                hasProperty("age", is(43))
                        )
                )));

    }
    
    
    @Test
    public void itShouldShowFilledPersonEditForm() throws Exception {
        //given a request to show create person page
        Person person = new Person();
        person.setId(1l);
        person.setAge(23);
        person.setFirstName("Peter");
        person.setLastName("Kirk");
        
        when(personService.findPersonById(1l)).thenReturn(person);
        //when
        mockMvc.perform(get("/edit/1"))
                //then the module attribute should have Person POJO by attribute key person        
                .andExpect(model().attribute("person", instanceOf(Person.class)))
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("person", equalTo(person)))
                //and response shoud be ok
                .andExpect(status().isOk());
        
        verify(personService).findPersonById(1l);
    }
    
     @Test
    public void itShouldUpdateValidEditPersonDataAndRedirectToListingPageWithFlashMessage() throws Exception {
        //given a request with params
        ArgumentCaptor<Person> argument = ArgumentCaptor.forClass(Person.class);
        //when a post request is made to /create page
        mockMvc.perform(post("/update/1")
                .param("firstName", "Peter")
                .param("lastName", "Parker")
                .param("age", "23")).andExpect(status().isMovedTemporarily())
                //then it should redirect to list page
                .andExpect(redirectedUrl("/list"))
                .andExpect(model().hasNoErrors())
                //and flash map should contain success message
                .andExpect(flash().attributeExists("sucMsg"));
        //and the pojo passed to save method as properties set as parameter passed
        verify(personService).update(argument.capture());
        
        Assert.assertEquals("Peter", argument.getValue().getFirstName());
        Assert.assertEquals("Parker", argument.getValue().getLastName());
        Assert.assertEquals(23, argument.getValue().getAge().intValue());
        Assert.assertEquals(1l, argument.getValue().getId().longValue());
    }
     
     @Test
    public void itShouldNotUpdateInValidEditPersonDataAndAndForwardBackToNewCustomerPageWithErrors() throws Exception {
        //given a request with params
        //when a post request is made to /update/1 page
        mockMvc.perform(post("/update/1")
                .param("firstName", "Peter")
                .param("lastName", "")
                .param("age", "23")).andExpect(status().isOk())
                //then it should forward back to edit page with filled in fields returnred back
                .andExpect(view().name("edit"))
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(2))
                .andExpect(model().attribute("person", hasProperty("firstName", is("Peter"))))
                .andExpect(model().attribute("person", hasProperty("age", is(23))))
                .andExpect(model().attributeHasFieldErrors("person", "lastName"));
        //and update method is never called
        verify(personService,never()).update(Mockito.any(Person.class));
    } 
    
    @Test
    public void itShouldDeleteEntryAndRedirectToListingPageWithFlashMessage() throws Exception {
        //given a request to delete
        //when a post request is made to /delete/1 page
        mockMvc.perform(post("/delete/1"))
                //then it should redirect to list page
                .andExpect(status().isMovedTemporarily())
                .andExpect(redirectedUrl("/list"))
                //and flash map should contain success message
                .andExpect(flash().attributeExists("sucMsg"));
        //and the delete id passed as path variable should be passed to delete function
        verify(personService).delete(1l);
    }
}
