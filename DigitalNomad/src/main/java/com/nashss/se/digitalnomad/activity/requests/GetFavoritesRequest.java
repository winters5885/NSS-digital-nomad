package com.nashss.se.digitalnomad.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetFavoritesRequest.Builder.class)
public class GetFavoritesRequest {
    private final String userId;

    /**
     * Instantiates a new GetFavoritesRequest object.
     *
     * @param userId A userId tied to a Favorites list.
     */
    public GetFavoritesRequest(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "GetFavoritesRequest{" +
                "userId='" + userId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static GetFavoritesRequest.Builder builder() {
        return new GetFavoritesRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String userId;

        public Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public GetFavoritesRequest build() {

            return new GetFavoritesRequest(userId);
        }
    }
}

