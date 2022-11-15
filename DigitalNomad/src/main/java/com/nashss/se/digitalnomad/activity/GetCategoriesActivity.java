package com.nashss.se.musicplaylistservice.activity;

import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
//import com.nashss.se.digitalnomad.activity.requests.GetCategoryRequest;
//import com.nashss.se.digitalnomad.activity.results.GetPlaylistResult;
//import com.nashss.se.digitalnomad.converters.ModelConverter;
//import com.nashss.se.digitalnomad.dynamodb.PlaylistDao;
//import com.nashss.se.digitalnomad.dynamodb.models.Playlist;
//import com.nashss.se.digitalnomad.dynamoDb.models.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * Implementation of the GetPlaylistActivity for the MusicPlaylistService's GetPlaylist API.
 *
 * This API allows the customer to get one of their saved playlists.
 */
public class GetPlaylistActivity {
    private final Logger log = LogManager.getLogger();
    private final CategoryDao categoryDao;

    /**
     * Instantiates a new GetPlaylistActivity object.
     *
     * @param categoryDao PlaylistDao to access the playlist table.
     */
    @Inject
    public GetCategoriesActivity(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistRequest request object containing the playlist ID
     * @return getPlaylistResult result object containing the API defined {@link PlaylistModel}
     */
    public GetCategoryResult handleRequest(final GetCategoryRequest getPlaylistRequest) {
        log.info("Received GetPlaylistRequest {}", getCategoryRequest);
        String requestedId = getPlaylistRequest.getId();
        Playlist playlist = categoryDao.getPlaylist(requestedId);
        PlaylistModel playlistModel = new ModelConverter().toPlaylistModel(playlist);

        return GetPlaylistResult.builder()
                .withPlaylist(playlistModel)
                .build();
    }
}
