package com.nashss.se.digitalnomad.dynamoDb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "categories")
public class Category {
    private String category;

    /**
     * Empty constructor for Category POJO.
     */
    public Category() {
    }

    /**
     * Non-empty constructor for Category POJO.
     * @param category category parameter
     */
    public Category(String category) {
        this.category = category;
    }

    @DynamoDBHashKey(attributeName = "category")
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
        return Objects.equals(category, category1.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category);
    }

    @Override
    public String toString() {
        return "Category{" +
                "categoryList=" + category +
                '}';
    }
}
