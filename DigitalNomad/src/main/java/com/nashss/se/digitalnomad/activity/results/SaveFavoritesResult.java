package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.models.FavoriteModel;

public class SaveFavoritesResult {
    private final FavoriteModel favoriteModel;

    /**
     * Instantiates a new SaveFavoritesResult object.
     *
     * @param favoriteModel The favorite model.
     */
    public SaveFavoritesResult(FavoriteModel favoriteModel) {
        this.favoriteModel = favoriteModel;
    }

    public FavoriteModel getFavoriteModel() {
        return favoriteModel;
    }

    @Override
    public String toString() {
        return "SaveFavoritesResult{" +
                "favoriteModel=" + favoriteModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private FavoriteModel favoriteModel;

        public Builder withFavoriteModel(FavoriteModel favoriteModel) {
            this.favoriteModel = favoriteModel;
            return this;
        }

        public SaveFavoritesResult build() {
            return new SaveFavoritesResult(favoriteModel);
        }
    }
}
