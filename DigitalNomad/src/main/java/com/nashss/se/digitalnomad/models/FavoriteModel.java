package com.nashss.se.digitalnomad.models;

import java.util.List;

public class FavoriteModel {
    private String userId;
    private List<String> destinations;

    /**
     * Non-Empty constructor for DestinationModel.
     * @param userId city parameter
     * @param destinations country parameter

     */
    public FavoriteModel(String userId, List<String> destinations) {
        this.userId = userId;
        this.destinations = destinations;
    }

    public String getUserId() {
        return userId;
    }

    public List<String> getDestinations() {
        return destinations;
    }

    @Override
    public String toString() {
        return "FavoriteModel{" +
                ", userId='" + userId + '\'' +
                ", destinations='" + destinations + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static FavoriteModel.Builder builder() {
        return new FavoriteModel.Builder();
    }

    public static class Builder {
        private String userId;
        private List<String> destinations;

public FavoriteModel.Builder withUserId(String userId) {
            this.userId = userId;
            return this;
        }

        public FavoriteModel.Builder withDestinations(List<String> destinations) {
            this.destinations = destinations;
            return this;
        }

        public FavoriteModel build() {
            return new FavoriteModel(userId, destinations);
        }
    }
}
