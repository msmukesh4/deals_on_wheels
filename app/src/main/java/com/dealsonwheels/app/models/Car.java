package com.dealsonwheels.app.models;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by mukesh on 20/9/17.
 */

public class Car {
    @SerializedName("ProductId")
    int productId;
    @SerializedName("ProductName")
    String productName;
    @SerializedName("YearOfMake")
    int yearOfMake;
    @SerializedName("Kilometer")
    long kilometer;
    @SerializedName("FuelType")
    String fuelType;
    @SerializedName("Transmission")
    String transmission;
    @SerializedName("SoldBy")
    String soldBy;
    @SerializedName("NoOfOwner")
    String numberOfOwner;
    @SerializedName("RegisteredAt")
    String registeredAt;
    @SerializedName("Insurance")
    String insurance;
    @SerializedName("LifeTimeTax")
    String lifeTimeTax;
    @SerializedName("ProfileID")
    int profileId;
    @SerializedName("PrimaryImageURL")
    String primaryImageUrl;
    @SerializedName("FirstName")
    String firstName;
    @SerializedName("MiddileName")
    String middleName;
    @SerializedName("LastName")
    String lastName;
    @SerializedName("Location")
    String location;
    @SerializedName("Chanel")
    String channel;
    @SerializedName("ProfileImageURL")
    String profileImageUrl;
    @SerializedName("Price")
    long price;
    @SerializedName("extraImages")
    String[] extraImages;


    public Car(int productId, String productName){
        this.productId = productId;
        this.productName = productName;
    };

    public Car(int productId, String productName, int yearOfMake,
               long kilometer, String fuelType, String transmission,
               String soldBy, String numberOfOwner, String registeredAt,
               String insurance, String lifeTimeTax, int profileId, String primaryImageUrl,
               String firstName, String middleName, String lastName, String location, String channel, String profileImageUrl, long price, String[] extraImages) {
        this.productId = productId;
        this.productName = productName;
        this.yearOfMake = yearOfMake;
        this.kilometer = kilometer;
        this.fuelType = fuelType;
        this.transmission = transmission;
        this.soldBy = soldBy;
        this.numberOfOwner = numberOfOwner;
        this.registeredAt = registeredAt;
        this.insurance = insurance;
        this.lifeTimeTax = lifeTimeTax;
        this.profileId = profileId;
        this.primaryImageUrl = primaryImageUrl;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.location = location;
        this.channel = channel;
        this.profileImageUrl = profileImageUrl;
        this.price = price;
        this.extraImages = extraImages;
    }

    @Override
    public String toString() {
        return "Car{" +
                "productId=" + productId +
                ", productName='" + productName + '\'' +
                ", yearOfMake=" + yearOfMake +
                ", kilometer=" + kilometer +
                ", fuelType='" + fuelType + '\'' +
                ", transmission='" + transmission + '\'' +
                ", soldBy='" + soldBy + '\'' +
                ", numberOfOwner='" + numberOfOwner + '\'' +
                ", registeredAt='" + registeredAt + '\'' +
                ", insurance='" + insurance + '\'' +
                ", lifeTimeTax='" + lifeTimeTax + '\'' +
                ", profileId=" + profileId +
                ", primaryImageUrl='" + primaryImageUrl + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", location='" + location + '\'' +
                ", channel='" + channel + '\'' +
                ", profileImageUrl='" + profileImageUrl + '\'' +
                ", price=" + price +
                ", extraImages=" + extraImages +
                '}';
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getYearOfMake() {
        return yearOfMake;
    }

    public void setYearOfMake(int yearOfMake) {
        this.yearOfMake = yearOfMake;
    }

    public long getKilometer() {
        return kilometer;
    }

    public void setKilometer(long kilometer) {
        this.kilometer = kilometer;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getSoldBy() {
        return soldBy;
    }

    public void setSoldBy(String soldBy) {
        this.soldBy = soldBy;
    }

    public String getNumberOfOwner() {
        return numberOfOwner;
    }

    public void setNumberOfOwner(String numberOfOwner) {
        this.numberOfOwner = numberOfOwner;
    }

    public String getRegisteredAt() {
        return registeredAt;
    }

    public void setRegisteredAt(String registeredAt) {
        this.registeredAt = registeredAt;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }

    public String getLifeTimeTax() {
        return lifeTimeTax;
    }

    public void setLifeTimeTax(String lifeTimeTax) {
        this.lifeTimeTax = lifeTimeTax;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public String getPrimaryImageUrl() {
        return primaryImageUrl;
    }

    public void setPrimaryImageUrl(String primaryImageUrl) {
        this.primaryImageUrl = primaryImageUrl;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public String[] getExtraImages() {
        return extraImages;
    }

    public void setExtraImages(String[] extraImages) {
        this.extraImages = extraImages;
    }
}
