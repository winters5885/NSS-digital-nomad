package com.nashss.se.web_crawler;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;

@DynamoDBTable(tableName = "destinations")
public class Destination implements Serializable {
    private String city;
    private String country;
    private String locationName;
    private String category;
    private String destinationID;

    Destination() {}
    Destination(String city, String country, String locationName, String category, String destinationID) {
        this.city = city;
        this.country = country;
        this.locationName = locationName;
        this.category = category;
        this.destinationID = destinationID;
    }

    public void setCity(String city) {
        this.city = city;
    }
    @DynamoDBAttribute(attributeName = "cityName")
    public String getCity() {
        return city;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    @DynamoDBAttribute(attributeName = "country")
    public String getCountry() {
        return country;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    @DynamoDBAttribute(attributeName = "locationName")
    public String getLocationName() {
        return locationName;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    @DynamoDBHashKey(attributeName = "category")
    public String getCategory() {
        return category;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }
    @DynamoDBRangeKey(attributeName = "destinationId")
    public String getDestinationID() {
        return destinationID;
    }
}
