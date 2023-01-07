package com.nashss.se.digitalnomad.dynamoDb;

import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

import static com.nashss.se.digitalnomad.dynamoDb.models.Destination.DESTINATION_GSI_INDEX;


public class FavoritesDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a FavoritesDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the favorites table
     */
    @Inject
    public FavoritesDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Saves (creates or updates) the given favorites.
     * @param favorite The destination to save
     * @return The favorite object that was saved
     */
    public Favorite saveFavorites(Favorite favorite) {
        this.dynamoDbMapper.save(favorite);
        return favorite;
    }

    /**
     * Saves (creates or updates) the given favorites.
     * @param userId The favorite to save
     * @return The favorite object that was saved
     */
    public List<Destination> getFavorites(String userId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);

        DynamoDBQueryExpression<Favorite> queryExpression = new DynamoDBQueryExpression<Favorite>()
                .withHashKeyValues(favorite);

        PaginatedQueryList<Favorite> userFavorites = dynamoDbMapper.query(Favorite.class, queryExpression);

        List<Favorite> convertedList = new ArrayList<>(userFavorites);

        List<Destination> returnedDestinations = new ArrayList<>();

        for (Favorite savedFavorite : convertedList) {
            List<String> destinationIds = savedFavorite.getDestinations();
            for (String particularDestinationId : destinationIds) {
                Map<String, AttributeValue> valueMap = new HashMap<>();
                valueMap.put(":destinationId", new AttributeValue().withS(particularDestinationId));

                DynamoDBQueryExpression<Destination> queryExpression1 =
                        new DynamoDBQueryExpression<Destination>()
                                .withIndexName(DESTINATION_GSI_INDEX)
                                .withConsistentRead(false)
                                .withKeyConditionExpression("destinationId = :destinationId")
                        .withExpressionAttributeValues(valueMap);

                PaginatedQueryList<Destination> returnDestinations =
                        dynamoDbMapper.query(Destination.class, queryExpression1);

                List<Destination> anotherConvertedList = new ArrayList<>(returnDestinations);
                for (Destination finalDestination : anotherConvertedList) {
                    returnedDestinations.add(finalDestination);
                }

            }
        }

        return returnedDestinations;
    }
}
