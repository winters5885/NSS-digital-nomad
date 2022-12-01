package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;


public class FavoritesDao {

    private final DynamoDBMapper dynamoDbMapper;

    /**
     * Instantiates a PlaylistDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the playlists table
     */
    @Inject
    public FavoritesDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Saves (creates or updates) the given favorites.
     * @param favorite The playlist to save
     * @return The favorite object that was saved
     */
    public Favorite saveFavorites(Favorite favorite) {
        this.dynamoDbMapper.save(favorite);
        return favorite;
    }

    /**
     * Saves (creates or updates) the given favorites.
     * @param userId The playlist to save
     * @return The favorite object that was saved
     */
    public List<Destination> getFavorites(String userId) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);

        DynamoDBQueryExpression<Favorite> queryExpression = new DynamoDBQueryExpression<Favorite>()
                .withHashKeyValues(favorite);

        PaginatedQueryList userFavorites = dynamoDbMapper.query(Favorite.class, queryExpression);

        List<Favorite> convertedList = new ArrayList<>(userFavorites);

        List<Destination> returnedDestinations = new ArrayList<>();

        for (Favorite savedFavorite : convertedList) {
            List<String> destinationIds = savedFavorite.getDestinations();
            for (String particularDestinationId : destinationIds) {
                Destination destination = new Destination();
                destination.setDestinationID(particularDestinationId);
                Destination destinationFromGSI = dynamoDbMapper.load(Destination.class, destination);
                returnedDestinations.add(destinationFromGSI);
            }
        }

        return returnedDestinations;

//        public void main() {
//            getFavorites("test");
//        }


        //query the favorites table to get the list of favorites for a particular userId   X
        //once we have the list, for each destinationID, we query the destinationsGSI and get all the
        // relevant destination information and put into another list of destinations
        //return the list of destinations

        //GSI name is : DestinationsGSIIndex
    }
}
