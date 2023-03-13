package com.example.busManagement.controller;

import com.example.busManagement.domain.Person;
import com.example.busManagement.exception.PersonNotFoundException;
import com.example.busManagement.repository.IRepositoryPerson;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
class ControllerPerson {

    private final IRepositoryPerson repository;

    ControllerPerson(IRepositoryPerson repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/people")  //GETALL
    List<Person> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/people")   // ADD
    Person newPerson(@RequestBody Person newPerson) {
        return repository.save(newPerson);
    }

    // Single item

    @GetMapping("/people/{id}")     //GET BY ID
    Person one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @PutMapping("/people/{id}")     //UPDATE
    Person replacePerson(@RequestBody Person newPerson, @PathVariable Long id) {

        return repository.findById(id)
                .map(Person -> {
                    Person.setFirstName(newPerson.getFirstName());
                    Person.setLastName(newPerson.getLastName());
                    Person.setDateOfBirth(newPerson.getDateOfBirth());
                    Person.setGender(newPerson.getGender());
                    Person.setPhoneNumber(newPerson.getPhoneNumber());
                    return repository.save(Person);
                })
                .orElseGet(() -> { // otherwise if not found, ADD IT
                    newPerson.setId(id);
                    return repository.save(newPerson);
                });
    }

    @DeleteMapping("/people/{id}")  //DELETE
    void deletePerson(@PathVariable Long id) {
        repository.deleteById(id);
    }


    @GetMapping("/people/higherThan/{value}")
    List<Person> higherThan(@PathVariable int value) {
        return repository.findAll()
                .stream()
                .filter(person -> person.getId() > value)
                .collect(Collectors.toList());
    }


}
