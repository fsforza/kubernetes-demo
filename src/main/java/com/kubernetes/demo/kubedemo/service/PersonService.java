package com.kubernetes.demo.kubedemo.service;

import java.util.ArrayList;
import java.util.List;

import com.kubernetes.demo.kubedemo.model.Person;
import com.kubernetes.demo.kubedemo.repository.PersonRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * PersonService
 */
@Service
public class PersonService {

    @Autowired
    PersonRepository personRepository;
    
    public List<Person> getAllPersons() {
        List<Person> persons = new ArrayList<Person>();
        personRepository.findAll().forEach(person -> persons.add(person));
        return persons;
    }

    public Person getPersonById(int id) {
        return personRepository.findById(id).get();
    }

    public void saveOrUpdate(Person person) {
        personRepository.save(person);
    }

    public void delete(int id) {
        personRepository.deleteById(id);
    }
}
