package com.nashss.se.digitalnomad.models;

import com.nashss.se.digitalnomad.dynamoDb.models.Destination;

import java.util.List;

public class DestinationModel {
    private String city;
    private String country;
    private String locationName;
    private String category;
    private String destinationID;

    /**
     * Empty constructor for Category POJO.
     */
    public DestinationModel() {
    }

    /**
     * Non-Empty constructor for Category POJO.
     * @param city city parameter
     * @param country country parameter
     * @param locationName locationName parameter
     * @param category category parameter
     * @param destinationID destinationID parameter
     */
    public DestinationModel(String city, String country, String locationName, String category, String destinationID) {
        this.city = city;
        this.country = country;
        this.locationName = locationName;
        this.category = category;
        this.destinationID = destinationID;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getLocationName() {
        return locationName;
    }

    public String getCategory() {
        return category;
    }

    public String getDestinationID() {
        return destinationID;
    }



    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }


    @Override
    public String toString() {
        return "DestinationModel{" +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", locationName='" + locationName + '\'' +
                ", category='" + category + '\'' +
                ", destinationID='" + destinationID + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static DestinationModel.Builder builder() {
        return new DestinationModel.Builder();
    }

    public static class Builder {
        private String city;
        private String country;
        private String locationName;
        private String category;
        private String destinationID;

        public DestinationModel.Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public DestinationModel.Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public DestinationModel.Builder withLocationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public DestinationModel.Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public DestinationModel.Builder withDestinationID(String destinationID) {
            this.destinationID = destinationID;
            return this;
        }

        public DestinationModel build() {

            return new DestinationModel(city, country, locationName, category, destinationID);
        }
    }
}
