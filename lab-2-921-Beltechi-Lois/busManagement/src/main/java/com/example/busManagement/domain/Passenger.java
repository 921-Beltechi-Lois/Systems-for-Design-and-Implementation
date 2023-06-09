package com.example.busManagement.domain;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

/*
        {
        "timesTravelled": "101",
        "firstName": "sabina",
        "lastName": "hhhhhdsah",
        "dateOfBirth": "2020/01/11",
        "gender": "Female",
        "phoneNumber": "112"
        }

  */
@Entity
@Table(name="mt_passenger")
public class Passenger{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="passenger_id")
    private long id;


    //FetchType.LAZY
    @OneToMany(mappedBy = "passenger", fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    private Set<Luggage> luggages;


    @Column(name="passenger_timesTravelled")
    private String timesTravelled;

    @Column(name="passenger_firstName")
    private  String firstName;

    @Column(name="passenger_lastName")
    private  String lastName;

    @Column(name="passenger_dateOfBirth")
    private  String dateOfBirth;

    @Column(name="passenger_gender")
    private  String gender;

    @Column(name="passenger_phoneNumber")
    private  String phoneNumber;

    public Passenger(String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber,
                     String timesTravelled) {
        this.timesTravelled = timesTravelled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Passenger(long id,String firstName, String lastName, String dateOfBirth, String gender, String phoneNumber,
                     String timesTravelled) {
        this.id=id;
        this.timesTravelled = timesTravelled;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public Passenger() {

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Set<Luggage> getLuggages() {
        return luggages;
    }

    public void setLuggages(Set<Luggage> luggages) {
        this.luggages = luggages;
    }

    public String getTimesTravelled() {
        return timesTravelled;
    }

    public void setTimesTravelled(String timesTravelled) {
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

//    public PassengerDTO toPassengerDTO (){
//        PassengerDTO passengerdto= new PassengerDTO();
//        passengerdto.setId(this.getId());
//        passengerdto.setGender(this.getGender());
//        passengerdto.setFirstName(this.getFirstName());
//        passengerdto.setTimesTravelled(this.getTimesTravelled());
//        passengerdto.setLastName(this.getLastName());
//        passengerdto.setDateOfBirth(this.getDateOfBirth());
//        passengerdto.setPhoneNumber(this.getPhoneNumber());
//        return passengerdto;
//    }
}
