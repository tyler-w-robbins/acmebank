package com.acme.model.models;

import com.acme.model.deserializers.CustomSSNDeserializer;
import com.acme.model.serializers.CustomSSNSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class Borrower {

    private int borrowerId;
    
    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private int age;

    @NotNull
    private String address;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    private int zip;

    @NotNull
    @JsonSerialize(using = CustomSSNSerializer.class)
    @JsonDeserialize(using = CustomSSNDeserializer.class)
    private int ssn;

    @NotNull
    private String relationship;

    @NotNull(message = "No employment")
    @Valid
    private List<Employer> employment;

    public void setBorrowerId(int borrowerId) {
        this.borrowerId = borrowerId;
    }

    public int getBorrowerId() {
        return borrowerId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getSsn() {
        return ssn;
    }

    public void setSsn(int ssn) {
        this.ssn = ssn;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public List<Employer> getEmployment() {
        return employment;
    }

    public void setEmployerList(List<Employer> employment) {
        this.employment = employment;
    }

}
