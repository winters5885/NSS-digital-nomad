package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import com.nashss.se.digitalnomad.models.CategoryModel;

public class GetCategoriesResult {
    private final CategoryModel category;

    private GetCategoriesResult(CategoryModel category) {
        this.category = category;
    }

    public CategoryModel getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "GetPlaylistResult{" +
                "playlist=" + category +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private CategoryModel category;

        public Builder withPlaylist(CategoryModel category) {
            this.category = category;
            return this;
        }

        public GetCategoriesResult build() {
            return new GetCategoriesResult(category);
        }
    }
}
