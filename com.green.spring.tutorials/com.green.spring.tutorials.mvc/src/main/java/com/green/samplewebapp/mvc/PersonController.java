package com.green.samplewebapp.mvc;

import com.green.samplewebapp.domain.Person;
import com.green.samplewebapp.service.PersonService;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Person Controller class responsible for managing http interaction and passing
 * required data to service class for processing
 * 
 * @author gaurav
 */
@Controller
public class PersonController {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PersonController.class);
    
    @Autowired private PersonService personService;
 
    /**
     * Shows the Person creation page.
     * 
     * @param model
     * @return view name
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String _new(Model model){
        LOGGER.info("Got request for person display page.");
        Person person = new Person();
        model.addAttribute("person",person);
        return "new";
    }
    
    /**
     * Creates a new Person with validation
     * 
     * @param person
     * @param bindingResult
     * @param redirectAttributes
     * @return view name
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String save(@ModelAttribute @Valid Person person, BindingResult bindingResult,RedirectAttributes redirectAttributes){
        try{
            if(bindingResult.hasErrors()){
                LOGGER.info("Forms had error !!!!!");
                return "new";
            }
        
            personService.save(person);
            redirectAttributes.addFlashAttribute("sucMsg", "Person created.");
            LOGGER.info("Saved person.");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errMsg", "Problem in creating person");
            LOGGER.error("Problem in creating person",e);
        }
        return "redirect:list";
    }
    
    /**
     * Lists all person in the system
     * 
     * @param model
     * @return view name
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model){
        try{
            LOGGER.info("Listing all persons ....");
            model.addAttribute("personList", personService.finAllPerson());
        }catch(Exception e){
            model.addAttribute("errMsg", "Problem in listing persons.");
            LOGGER.error("Problem in listing persons.",e);
        }
        return "list";
    }
    
    /**
     * Shows edit page for the id(Person) asked for
     * 
     * @param id
     * @param model
     * @return view name
     */
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String edit(@PathVariable Long id,Model model){
        try{
            LOGGER.info("Showing edit page for person with id {}",id);
            Person person = personService.findPersonById(id);
            model.addAttribute("person",person);
        }catch(Exception e){
            model.addAttribute("errMsg", "Problem in edit person.");
            LOGGER.error("Problem in edit person.",e);
        }
        return "edit";
    }
    
    /**
     * Updates an existing Person with validation
     * 
     * @param id
     * @param person
     * @param bindingResult
     * @param redirectAttributes
     * @return view name
     */
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable Long id,@ModelAttribute @Valid Person person,BindingResult bindingResult, RedirectAttributes redirectAttributes){
        try{
            LOGGER.info("Updating person with id {}",id);
            if(bindingResult.hasErrors()){
                LOGGER.info("Forms had error !!!!!");
                return "edit";
            }
            person.setId(id);

            personService.update(person);
            redirectAttributes.addFlashAttribute("sucMsg", "Person updated.");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errMsg", "Problem in updating person");
            LOGGER.error("Problem in updating person with id " + id,e);
        }
        return "redirect:/list";
    }
    
    /**
     * Deletes an existing Person
     * @param id
     * @param redirectAttributes
     * @return 
     */
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    public String delete(@PathVariable Long id,RedirectAttributes redirectAttributes){
        try{
            LOGGER.info("Deleting person with id {}",id);
            personService.delete(id);
            redirectAttributes.addFlashAttribute("sucMsg", "Person deleted.");
        }catch(Exception e){
            redirectAttributes.addFlashAttribute("errMsg", "Problem in deleting person");
            LOGGER.error("Problem in updating person with id " + id,e);
        }
        return "redirect:/list";
    }
}
