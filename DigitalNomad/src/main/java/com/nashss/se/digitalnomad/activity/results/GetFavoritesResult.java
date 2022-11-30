package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.models.FavoriteModel;

import java.util.List;

public class GetFavoritesResult {
    private final List<FavoriteModel> favoriteModels;

    /**
     * Instantiates a new SaveFavoritesResult object.
     *
     * @param favoriteModels The favorite model list.
     */
    public GetFavoritesResult(List<FavoriteModel> favoriteModels) {
        this.favoriteModels = favoriteModels;
    }

    public List<FavoriteModel> getFavoriteModels() {
        return favoriteModels;
    }

    @Override
    public String toString() {
        return "GetFavoritesResult{" +
                "favoriteModels=" + favoriteModels +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<FavoriteModel> favoriteModels;

        public Builder withFavoriteModel(List<FavoriteModel> favoriteModels) {
            this.favoriteModels = favoriteModels;
            return this;
        }

        public GetFavoritesResult build() {
            return new GetFavoritesResult(favoriteModels);
        }
    }
}
