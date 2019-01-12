package com.kubernetes.demo.kubedemo.repository;

import com.kubernetes.demo.kubedemo.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {
}