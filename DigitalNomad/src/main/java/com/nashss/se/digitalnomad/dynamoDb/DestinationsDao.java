package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.digitalnomad.Exceptions.CategoryNotFoundException;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.List;

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
    public List<Destination> getDestinations(String category) {

        Destination destination = new Destination();
        destination.setCategory(category);

        DynamoDBQueryExpression<Destination> queryExpression = new DynamoDBQueryExpression<Destination>()
                .withHashKeyValues(destination);

        PaginatedQueryList<Destination> destinationList = dynamoDbMapper.query(Destination.class, queryExpression);

        if (destinationList.isEmpty()) {
            throw new CategoryNotFoundException(
                    String.format("Could not find destination in '%s'", category));
        }

        return destinationList;
    }
}
