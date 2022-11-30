package com.nashss.se.digitalnomad.dynamoDb;

import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

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
}
