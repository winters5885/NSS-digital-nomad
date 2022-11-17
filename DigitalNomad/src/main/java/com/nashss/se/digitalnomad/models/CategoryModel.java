package com.nashss.se.digitalnomad.models;

import java.util.List;
import java.util.Objects;

public class CategoryModel {
    private List<String> categoriesList;

    /**
     * Empty constructor for Category POJO.
     */
    public CategoryModel() {
    }

    /**
     * Non-Empty constructor for Category POJO.
     * @param categoriesList categories parameter
     */
    public CategoryModel(List<String> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public List<String> getCategories() {
        return categoriesList;
    }

    public void setCategory(List<String> categories) {
        this.categoriesList = categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CategoryModel that = (CategoryModel) o;
        return Objects.equals(categoriesList, that.categoriesList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(categoriesList);
    }

    @Override
    public String toString() {
        return "Category{" +
                "category='" + categoriesList + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {

        return new Builder();
    }

    public static class Builder {
        private List<String> categoryList;

        public Builder withCategory(List<String> categoryList) {
            this.categoryList = categoryList;
            return this;
        }

        public CategoryModel build() {

            return new CategoryModel(categoryList);
        }
    }
}
