package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.nashss.se.digitalnomad.Exceptions.CategoryNotFoundException;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, AttributeValue> valueMap = new HashMap<>();

        valueMap.put(":category", new AttributeValue().withS(category));

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression()
                .withFilterExpression(":category = category")
                .withExpressionAttributeValues(valueMap);
        PaginatedScanList<Destination> destinationList = dynamoDbMapper.scan(Destination.class, scanExpression);

        if (destinationList.isEmpty()) {
            throw new CategoryNotFoundException(
                    String.format("Could not find destination in '%s'", category));
        }

        return destinationList;
    }
}
