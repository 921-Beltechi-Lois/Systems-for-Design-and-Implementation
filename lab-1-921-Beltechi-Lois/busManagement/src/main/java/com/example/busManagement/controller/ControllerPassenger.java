package com.example.busManagement.controller;

import com.example.busManagement.domain.Passenger;
import com.example.busManagement.exception.PassengerNotFoundException;
import com.example.busManagement.repository.IRepositoryPassenger;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ControllerPassenger {

    private final IRepositoryPassenger repository;

    ControllerPassenger(IRepositoryPassenger repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/passengers")  //GETALL
    List<Passenger> all() {
        return repository.findAll();
    }
    // end::get-aggregate-root[]

    @PostMapping("/passengers")   // ADD
    Passenger newPassenger(@RequestBody Passenger newPassenger) {
        return repository.save(newPassenger);
    }

    // Single item

    @GetMapping("/passengers/{id}")     //GET BY ID
    Passenger one(@PathVariable Long id) {

        return repository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PutMapping("/passengers/{id}")     //UPDATE
    Passenger replacePassenger(@RequestBody Passenger newPassenger, @PathVariable Long id) {

        return repository.findById(id)
                .map(Passenger -> {
                    Passenger.setFirstName(newPassenger.getFirstName());
                    Passenger.setLastName(newPassenger.getLastName());
                    Passenger.setDateOfBirth(newPassenger.getDateOfBirth());
                    Passenger.setGender(newPassenger.getGender());
                    Passenger.setPhoneNumber(newPassenger.getPhoneNumber());
                    return repository.save(Passenger);
                })
                .orElseGet(() -> { // otherwise if not found, ADD IT
                    newPassenger.setId(id);
                    return repository.save(newPassenger);
                });
    }

    @DeleteMapping("/passengers/{id}")  //DELETE
    void deletePassenger(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
