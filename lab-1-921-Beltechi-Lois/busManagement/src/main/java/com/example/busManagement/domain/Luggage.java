package com.example.busManagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.util.Objects;

@Entity
public class Luggage {
    private @Id
    @GeneratedValue Long id;

    @ManyToOne
    private Passenger passenger;   // A Passenger has much luggage's; Cheita spre Passenger Deci aici punem
    //private Long passengerId;

    private int busNumber;
    private int weight;
    private int size;
    private String owner;
    private String status;


    public Luggage() {
    }


    public Luggage(int busNumber, int weight, int size, String owner, String status) {
        this.busNumber = busNumber;
        this.weight = weight;
        this.size = size;
        this.owner = owner;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getBusNumber() {
        return busNumber;
    }

    public void setBusNumber(int busNumber) {
        this.busNumber = busNumber;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Luggage luggage = (Luggage) o;
        return busNumber == luggage.busNumber && weight == luggage.weight && size == luggage.size && Objects.equals(id, luggage.id) && Objects.equals(passenger, luggage.passenger) && Objects.equals(owner, luggage.owner) && Objects.equals(status, luggage.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, passenger, busNumber, weight, size, owner, status);
    }

    @Override
    public String toString() {
        return "Luggage{" +
                "id=" + id +
                ", passenger=" + passenger +
                ", busNumber=" + busNumber +
                ", weight=" + weight +
                ", size=" + size +
                ", owner='" + owner + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
