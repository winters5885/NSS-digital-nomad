package com.nashss.se.digitalnomad.models;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;

import java.util.Objects;

public class CategoryModel {
    private String category;

    /**
     * Empty constructor for Category POJO.
     */
    public CategoryModel() {
    }

    public CategoryModel(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category category1 = (Category) o;
        return Objects.equals(category, category1.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + category + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {
        private String category;

        public Builder withCategory(String category) {
            this.category = category;
            return this;
        }

        public CategoryModel build() {

            return new CategoryModel(category);
        }
    }
}
