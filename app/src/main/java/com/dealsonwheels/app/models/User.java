package com.dealsonwheels.app.models;

import android.location.Address;

/**
 * Created by mukesh on 17/9/17.
 */

public class User {
    private String firstName;
    private String lastName;
    private Address address;
    private String contactNumber;
    private String currentCitySearch;
    public StaticData staticData;

    public User(){
        staticData = new StaticData();
    }

    public User(String firstName, String lastName, Address address, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.contactNumber = contactNumber;
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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCurrentCitySearch() {
        return currentCitySearch;
    }

    public void setCurrentCitySearch(String currentCitySearch) {
        this.currentCitySearch = currentCitySearch;
    }
}
