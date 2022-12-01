package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.models.DestinationModel;

import java.util.List;

public class GetFavoritesResult {
    private final List<DestinationModel> destinationModels;

    /**
     * Instantiates a new SaveFavoritesResult object.
     *
     * @param destinationModels The favorite model list.
     */
    public GetFavoritesResult(List<DestinationModel> destinationModels) {
        this.destinationModels = destinationModels;
    }

    public List<DestinationModel> getDestinationModels() {
        return destinationModels;
    }

    @Override
    public String toString() {
        return "GetFavoritesResult{" +
                "destinationModels=" + destinationModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<DestinationModel> destinationModels;

        public Builder withFavoriteModel(List<DestinationModel> destinationModels) {
            this.destinationModels = destinationModels;
            return this;
        }

        public GetFavoritesResult build() {
            return new GetFavoritesResult(destinationModels);
        }
    }
}
