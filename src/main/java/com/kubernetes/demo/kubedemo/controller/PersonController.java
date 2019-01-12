package com.kubernetes.demo.kubedemo.controller;

import java.util.List;

import com.kubernetes.demo.kubedemo.model.Person;
import com.kubernetes.demo.kubedemo.service.PersonService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * PersonController
 */
@RestController
public class PersonController {

    Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    PersonService personService;

    @GetMapping("/persons")
    private List<Person> getAllPersons() {
        logger.info("Getting all the persons...");
        return personService.getAllPersons();
    }

    @GetMapping("/persons/{id}")
    private Person getPerson(@PathVariable("id") int id) {
        logger.info("Getting the person with id: {}...", id);
        return personService.getPersonById(id);
    }

    @DeleteMapping("/persons/{id}")
    private void deletePerson(@PathVariable("id") int id) {
        logger.info("Deleting the person with id: {}...", id);
        personService.delete(id);
    }
    
    @PostMapping("/persons")
    private int savePerson(@RequestBody Person person) {
        logger.info("Saving or Updating the person with id: {}...", person.getId());
        personService.saveOrUpdate(person);
        return person.getId();
    }
}