package com.nashss.se.digitalnomad.activity.requests;

public class GetCategoriesRequest {
    private final String id;

    private GetCategoriesRequest(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "GetCategoriesRequest{" +
                "id='" + id + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String id;

        public Builder withId(String id) {
            this.id = id;
            return this;
        }

        public GetCategoriesRequest build() {
            return new GetCategoriesRequest(id);
        }
    }
}
