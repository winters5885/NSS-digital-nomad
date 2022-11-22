package com.nashss.se.digitalnomad.activity.requests;

public class GetDestinationsRequest {
    private final String city;
    private final String country;
    private final String locationName;
    private final String category;
    private final String destinationID;

    private GetDestinationsRequest(String city, String country, String locationName,
                                   String category, String destinationID) {
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

    @Override
    public String toString() {
        return "GetDestinationsRequest{" +
                "city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", locationName='" + locationName + '\'' +
                ", category='" + category + '\'' +
                ", destinationID='" + destinationID + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetDestinationsRequest.Builder builder() {
        return new GetDestinationsRequest.Builder();
    }

    public static class Builder {
        private String city;
        private String country;
        private String locationName;
        private String category;
        private String destinationID;


        public Builder withCity(String city) {
            this.city = city;
            return this;
        }

        public Builder withCountry(String country) {
            this.country = country;
            return this;
        }

        public Builder withLocationName(String locationName) {
            this.locationName = locationName;
            return this;
        }

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public Builder withDestinationID(String destinationID) {
            this.destinationID = destinationID;
            return this;
        }


        public GetDestinationsRequest build() {
            return new GetDestinationsRequest(city, country, locationName, category, destinationID);
        }
    }
}
