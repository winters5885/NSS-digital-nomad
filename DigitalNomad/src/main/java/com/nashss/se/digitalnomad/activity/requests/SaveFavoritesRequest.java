package com.nashss.se.digitalnomad.activity.requests;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.List;
import java.util.UUID;

@JsonDeserialize(builder = SaveFavoritesRequest.Builder.class)
public class SaveFavoritesRequest {
    private final String userId;
    private final List<String> favoriteDestinations;

    /**
     * Instantiates a new SaveFavoritesRequest object.
     *
     * @param favoriteDestinations A list of favorite Destinations.
     */
    public SaveFavoritesRequest(List<String> favoriteDestinations) {
        this.userId = UUID.randomUUID().toString();
        this.favoriteDestinations = favoriteDestinations;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getFavoriteDestinations() {
        return favoriteDestinations;
    }


    @Override
    public String toString() {
        return "SaveFavoritesRequest{" +
                "userId='" + userId + '\'' +
                ", favoriteDestinations='" + favoriteDestinations + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static SaveFavoritesRequest.Builder builder() {
        return new SaveFavoritesRequest.Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private List<String> favoriteDestinations;

        public Builder withFavoriteDestinations(List<String> favoriteDestinations) {
            this.favoriteDestinations = favoriteDestinations;
            return this;
        }

        public SaveFavoritesRequest build() {

            return new SaveFavoritesRequest(favoriteDestinations);
        }
    }
}
