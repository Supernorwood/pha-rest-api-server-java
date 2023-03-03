package com.pha.health.person.model;

/**
 * Data Definition of a PHA Form A Person.
 */
public class PHAPerson {

    private String firstName;
    private String lastName;

    private String dateOfBirth;

    private String gender;
    private String mailingAddress;

    private String phoneNumber;
    private String emailAddress;

    private String dodID;

    private String service;
    private String rank;

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

    public String getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDodID() {
        return dodID;
    }

    public void setDodID(String dodID) {
        this.dodID = dodID;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "PHAPerson{" + "firstName='" + firstName + '\'' + ", lastName='" + lastName + '\'' + ", mailingAddress='" + mailingAddress + '\'' + ", phoneNumber='" + phoneNumber + '\'' + ", emailAddress='" + emailAddress + '\'' + ", dateOfBirth='" + dateOfBirth + '\'' + ", dodID='" + dodID + '\'' + ", gender='" + gender + '\'' + ", service='" + service + '\'' + ", rank='" + rank + '\'' + '}';
    }
}
