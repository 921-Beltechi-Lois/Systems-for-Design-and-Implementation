package com.example.busManagement.controller;

import com.example.busManagement.domain.Luggage;
import com.example.busManagement.domain.LuggageDTO;
import com.example.busManagement.domain.LuggageDTOWithId;
import com.example.busManagement.exception.LuggageNotFoundException;
import com.example.busManagement.repository.IRepositoryLuggage;
import com.example.busManagement.repository.IRepositoryPassenger;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import java.util.stream.Collectors;

@RestController
class ControllerLuggage {

    private final IRepositoryLuggage repository;


    ControllerLuggage(IRepositoryLuggage repository, IRepositoryPassenger passenger_repository) {
        this.repository = repository;
    }


    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/luggages")  //GETALL fara passengers, cu ID PASSENGER
    List<LuggageDTOWithId> all() {

        ModelMapper modelMapper= new ModelMapper();

        modelMapper.typeMap(Luggage.class, LuggageDTOWithId.class).addMapping(luggage -> luggage.getPassenger().getId(), LuggageDTOWithId::setPassenger_Id);

//        modelMapper.typeMap(Luggage.class, LuggageDTOWithId.class);
        return repository.findAll().stream()
                .map(luggage -> modelMapper.map(luggage, LuggageDTOWithId.class))
                .collect(Collectors.toList());


        //return repository.findAll();
        //old:
        //return repository.findAll().stream().map(m->m.toLuggageDTOWithId()).collect(Collectors.toList());

    }
    // end::get-aggregate-root[]

    @PostMapping("/luggages")   // ADD
    Luggage newLuggage(@RequestBody Luggage newLuggage) {
        return repository.save(newLuggage);
    }

    // Single item
    @GetMapping("/luggages/{id}")     //GET BY ID, cu passengers
    LuggageDTO one(@PathVariable Long id) {
    //a1
//        return repository.findById(id)
//                .orElseThrow(() -> new LuggageNotFoundException(id));
        //a2.1
        if (repository.findById(id).isEmpty())
            throw new LuggageNotFoundException(id);
        //return repository.findById(id).get().toLuggageDTO();

        ModelMapper modelMapper = new ModelMapper();
        //LuggageDTO luggageDTO = modelMapper.map(repository.findById(id).get(), LuggageDTO.class);
        LuggageDTO luggageDTO = modelMapper.map(repository.findById(id).get(), LuggageDTO.class);
        return luggageDTO;
    }

    @PutMapping("/luggages/{id}")     //UPDATE
    Luggage replaceLuggage(@RequestBody Luggage newLuggage, @PathVariable Long id) {

        return repository.findById(id)
                .map(Luggage -> {
                    //Luggage.setPassenger(newLuggage.getPassenger());
                    Luggage.setBusNumber(newLuggage.getBusNumber());
                    Luggage.setSize(newLuggage.getSize());
                    Luggage.setOwner(newLuggage.getOwner());
                    Luggage.setStatus(newLuggage.getStatus());
                    Luggage.setWeight(newLuggage.getWeight());
                    return repository.save(Luggage);
                })
                .orElseGet(() -> { // otherwise if not found, ADD IT
                    newLuggage.setId(id);
                    return repository.save(newLuggage);
                });
    }

    @DeleteMapping("/luggages/{id}")  //DELETE
    void deleteLuggage(@PathVariable Long id) {
        repository.deleteById(id);
    }


}
