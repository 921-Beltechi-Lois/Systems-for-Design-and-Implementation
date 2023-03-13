package com.example.busManagement.controller;

import com.example.busManagement.domain.Luggage;
import com.example.busManagement.domain.Passenger;
import com.example.busManagement.domain.PassengerDTO;
import com.example.busManagement.exception.PassengerNotFoundException;
import com.example.busManagement.repository.IRepositoryLuggage;
import com.example.busManagement.repository.IRepositoryPassenger;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/*
{
        "timesTravelled":7,
        "firstName":"carina",
        "lastName":"ggss",
        "dateOfBirth":"2020/03/11",
        "gender":"Female",
        "phoneNumber":"1323454"
        }
 */

@RestController
class ControllerPassenger {

    private final IRepositoryPassenger passenger_repository;
    private final IRepositoryLuggage luggage_repository;

    ControllerPassenger(IRepositoryPassenger passenger_repository, IRepositoryLuggage luggage_repository) {
        this.passenger_repository = passenger_repository;
        this.luggage_repository = luggage_repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/passengers")  //GETALL fara luggages
    List<PassengerDTO> all() {

        ModelMapper modelMapper = new ModelMapper();
        List<Passenger> passengers = passenger_repository.findAll(); // FARA LUGGAGES, ALL
        return passengers.stream()
                .map(passenger -> modelMapper.map(passenger, PassengerDTO.class))
                .collect(Collectors.toList());

    }
    // end::get-aggregate-root[]

    @PostMapping("/passengers")   // ADD
    Passenger newPassenger(@RequestBody Passenger newPassenger) {
        return passenger_repository.save(newPassenger);
    }

    // ~ Luggage = P
    // ~ Passenger=A
    @PostMapping("/passengers/{passengerId}/luggages")
     Luggage newLuggageForPassenger(@PathVariable Long passengerId, @RequestBody Luggage newLuggage) {
        // Find the Passenger by ID
        Passenger passenger = passenger_repository.findById(passengerId)
                .orElseThrow(() -> new PassengerNotFoundException(passengerId));

        // Check if the Luggage with the given ID already exists
        Luggage existingLuggage = null;
        for (Luggage luggage : luggage_repository.findAll()) {
            if (luggage.equals(newLuggage)) {
                existingLuggage = luggage;
                break;
            }
        }
        if (existingLuggage != null) {
            // If the Luggage already exists, set the Passenger property and return the existing Luggage
            existingLuggage.setPassenger(passenger);
            luggage_repository.save(existingLuggage);
            return existingLuggage;
        } else {
            // If the Luggage does not exist, set the Passenger property for the new Luggage
            newLuggage.setPassenger(passenger);
            // Save the new Luggage to the database
            Luggage savedLuggage = luggage_repository.save(newLuggage);
            // Add the new Luggage to the Passenger
            passenger.getLuggages().add(savedLuggage);
            // Save the Passenger to update the Luggages list
            passenger_repository.save(passenger);
            return savedLuggage;
        }
    }

    // Single item

    @GetMapping("/passengers/{id}")     //GET BY ID, cu LUGGAGES
    Passenger one(@PathVariable Long id) {

        return passenger_repository.findById(id)
                .orElseThrow(() -> new PassengerNotFoundException(id));
    }

    @PutMapping("/passengers/{id}")     //UPDATE
    Passenger replacePassenger(@RequestBody Passenger newPassenger, @PathVariable Long id) {

        return passenger_repository.findById(id)
                .map(Passenger -> {
                    Passenger.setTimesTravelled(newPassenger.getTimesTravelled());
                    Passenger.setFirstName(newPassenger.getFirstName());
                    Passenger.setLastName(newPassenger.getLastName());
                    Passenger.setDateOfBirth(newPassenger.getDateOfBirth());
                    Passenger.setGender(newPassenger.getGender());
                    Passenger.setPhoneNumber(newPassenger.getPhoneNumber());
                    return passenger_repository.save(Passenger);
                })
                .orElseGet(() -> { // otherwise if not found, ADD IT
                    newPassenger.setId(id);
                    return passenger_repository.save(newPassenger);
                });
    }

    @DeleteMapping("/passengers/{id}")  //DELETE
    void deletePassenger(@PathVariable Long id) {
        passenger_repository.deleteById(id);
    }


}

