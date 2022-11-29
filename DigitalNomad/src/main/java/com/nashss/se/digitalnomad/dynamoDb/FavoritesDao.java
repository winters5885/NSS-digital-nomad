package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;

import javax.inject.Inject;
import java.util.List;
import java.util.Set;

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
     * Returns the {@link Playlist} corresponding to the specified id.
     *
     * @param id the Playlist ID
     * @return the stored Playlist, or null if none was found.
     */

    /**
     * Saves (creates or updates) the given playlist.
     * @param favorite The playlist to save
     * @return The Playlist object that was saved
     */
    public Favorite saveFavorites(String userId, List<String> favoriteDestinations) {
        Favorite favorite = new Favorite();
        favorite.setUserId(userId);
        favorite.setDestinations(favoriteDestinations);
        this.dynamoDbMapper.save(favorite);
        return favorite;
    }
}
