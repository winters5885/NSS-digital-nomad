package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.digitalnomad.Exceptions.CategoryNotFoundException;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;

import javax.inject.Inject;

public class CategoryDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an CategoryDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the categories table
     */
    @Inject
    public CategoryDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a category by category name.
     * <p>
     * If not found, throws CategoryNotFoundException.
     *
     * @param category The category to look up
     * @return The corresponding category if found
     */
    public Category getCategory(String category) {
        Category destinationCategory = dynamoDbMapper.load(Category.class, category);
        if (null == category) {
            throw new CategoryNotFoundException(
                    String.format("Could not find category '%s'", category));
        }

        return destinationCategory;
    }
}

