package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.dynamoDb.models.Destination;

import java.util.List;

public class GetDestinationsResult {
    private final List<Destination> destinationList;
    private final String category;

    /**
     * Constructor for GetDestinationsResult.
     *
     * @param destinationList  the destination to convert
     */
    public GetDestinationsResult(List<Destination> destinationList, String category) {
        this.destinationList = destinationList;
        this.category = category;
    }

    public List<Destination> getDestinationList() {
        return destinationList;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "getDestinationsList{" +
                "destinations=" + destinationList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetDestinationsResult.Builder builder() {

        return new GetDestinationsResult.Builder();
    }

    public static class Builder {
        private List<Destination> destinationList;
        private String category;

        public GetDestinationsResult.Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public GetDestinationsResult build() {

            return new GetDestinationsResult(destinationList, category);
        }
    }
}
