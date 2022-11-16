package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.digitalnomad.Exceptions.CategoryNotFoundException;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;

import javax.inject.Inject;

public class DestinationsDao {
    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates an DestinationsDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the destinations table
     */
    @Inject
    public DestinationsDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves a destination by category.
     * <p>
     * If not found, throws DestinationNotFoundException.
     *
     * @param category The category to look up destinations by
     * @return The corresponding destination if found
     */
    public Destination getDestination(String category) {
        Destination destination = dynamoDbMapper.load(Destination.class, category);
        if (null == destination) {
            throw new CategoryNotFoundException(
                    String.format("Could not find destination in '%s'", category));
        }

        return destination;
    }
}
