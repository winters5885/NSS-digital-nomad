package com.nashss.se.digitalnomad.activity.requests;

public class GetDestinationsRequest {

    private final String category;
    private GetDestinationsRequest(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "GetDestinationResultsRequest{" +
                "category='" + category + '\'' +
                '}';
    }


    //CHECKSTYLE:OFF:Builder
    public static GetDestinationsRequest.Builder builder() {
        return new GetDestinationsRequest.Builder();
    }

    public static class Builder {
        private String category;

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }


        public GetDestinationsRequest build() {
            return new GetDestinationsRequest(category);
        }
    }
}
