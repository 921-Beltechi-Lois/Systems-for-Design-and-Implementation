package com.example.busManagement.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.util.Objects;

@Entity
public class Passenger{
    private @Id @GeneratedValue Long id;
    private Integer timesTravelled;

    private  String firstName;
    private  String lastName;
    private  String dateOfBirth;
    private  String gender;
    private  String phoneNumber;

    public Passenger(String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber,
                     Integer timesTravelled) {
        this.timesTravelled = timesTravelled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Passenger() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTimesTravelled() {
        return timesTravelled;
    }

    public void setTimesTravelled(Integer timesTravelled) {
        this.timesTravelled = timesTravelled;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Passenger passenger = (Passenger) o;
        return Objects.equals(id, passenger.id) && Objects.equals(timesTravelled, passenger.timesTravelled) && Objects.equals(firstName, passenger.firstName) && Objects.equals(lastName, passenger.lastName) && Objects.equals(dateOfBirth, passenger.dateOfBirth) && Objects.equals(gender, passenger.gender) && Objects.equals(phoneNumber, passenger.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, timesTravelled, firstName, lastName, dateOfBirth, gender, phoneNumber);
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", timesTravelled=" + timesTravelled +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
