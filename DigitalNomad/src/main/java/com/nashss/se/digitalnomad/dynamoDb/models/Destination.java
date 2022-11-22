package com.nashss.se.digitalnomad.dynamoDb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.io.Serializable;
import java.util.Objects;

@DynamoDBTable(tableName = "destinations")
public class Destination implements Serializable {
    private String city;
    private String country;
    private String locationName;
    private String category;
    private String destinationID;

    /**
     * Empty constructor for Destination POJO.
     */
    public Destination() {}

    /**
     *  Constructor with parameters for Category POJO.
     * @param city for city name
     * @param country for country
     * @param locationName for specific location information (Eiffel tower)
     * @param category for category (beach, nightlife, etc)
     * @param destinationID UUID for specific destination instance
     */

    public Destination(String city, String country, String locationName, String category, String destinationID) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Destination)) {
            return false;
        }
        Destination that = (Destination) o;
        return Objects.equals(city, that.city) &&
                Objects.equals(country, that.country) &&
                Objects.equals(locationName, that.locationName) &&
                Objects.equals(category, that.category) &&
                Objects.equals(destinationID, that.destinationID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, country, locationName, category, destinationID);
    }

    @Override
    public String toString() {
        return "Destination{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", locationName='" + locationName + '\'' +
                ", category='" + category + '\'' +
                ", destinationID='" + destinationID + '\'' +
                '}';
    }
}

