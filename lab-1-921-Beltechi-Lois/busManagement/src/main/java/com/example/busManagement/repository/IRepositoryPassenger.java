package com.example.busManagement.repository;

import com.example.busManagement.domain.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IRepositoryPassenger extends JpaRepository<Passenger, Long> {

}
