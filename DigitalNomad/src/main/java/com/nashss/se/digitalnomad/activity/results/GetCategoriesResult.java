package com.nashss.se.digitalnomad.activity.results;

import java.util.List;

public class GetCategoriesResult {
    private final List<String> categoryList;

    /**
     * Constructor for GetCategoriesResult.
     * @param categoryList the playlist to convert
     */
    public GetCategoriesResult(List<String> categoryList) {
        this.categoryList = categoryList;
    }

    public List<String> getCategoriesResult() {
        return categoryList;
    }

    @Override
    public String toString() {
        return "GetCategoriesResult{" +
                "categories=" + categoryList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {
        private List<String> categoryStringList;

        public Builder withCategory(List<String> categories) {
            categoryStringList = categories;
            return this;
        }

        public GetCategoriesResult build() {

            return new GetCategoriesResult(categoryStringList);
        }
    }
}
