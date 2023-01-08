package com.nashss.se.digitalnomad.activity.requests;

public class GetCategoriesRequest {

    private GetCategoriesRequest() {

    }


    @Override
    public String toString() {
        return "GetCategoriesRequest" + "{'\"' +' '}";
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        public Builder withId(String id) {
            return this;
        }


        public GetCategoriesRequest build() {
            return new GetCategoriesRequest();
        }
    }
}
