package com.nashss.se.digitalnomad.activity.results;

import com.nashss.se.digitalnomad.models.CategoryModel;

import java.util.List;

public class GetCategoriesResult {
    private final List<CategoryModel> categories;

    public List<CategoryModel> getCategories() {

        return categories;
    }

    @Override
    public String toString() {
        return "GetCategoriesResult{" +
                "categories=" + categories +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {
        private List<CategoryModel> categories;

        public Builder withCategory(List<CategoryModel> categories) {
            this.categories = categories;
            return this;
        }

        public GetCategoriesResult build() {

            return new GetCategoriesResult();
        }
    }
}
