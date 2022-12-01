package com.nashss.se.digitalnomad.activity;

import com.nashss.se.digitalnomad.activity.requests.GetFavoritesRequest;
import com.nashss.se.digitalnomad.activity.results.GetFavoritesResult;
import com.nashss.se.digitalnomad.converters.ModelConverter;
import com.nashss.se.digitalnomad.dynamoDb.FavoritesDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;

import com.nashss.se.digitalnomad.models.DestinationModel;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;


/**
 * Implementation of the GetDestinationsActivity for Digital Nomad's GetDestinations API.
 *
 * This API allows the customer to get a list of destinations.
 */
public class GetFavoritesActivity {
    private final Logger log = LogManager.getLogger();
    private final FavoritesDao favoritesDao;

    /**
     * Instantiates a new GetDestinationsActivity object.
     *
     * @param favoritesDao DestinationsDao to access the destinations table.
     */
    @Inject
    public GetFavoritesActivity(FavoritesDao favoritesDao) {
        this.favoritesDao = favoritesDao;
    }

    /**
     * This method handles the incoming request by retrieving a user's favorite destinations from the database.
     * <p>
     * It then returns the destinations.
     * <p>
     *
     * @param getFavoritesRequest request object containing the destinations request
     * @return GetFavoritesResult result object
     */
    public GetFavoritesResult handleRequest(final GetFavoritesRequest getFavoritesRequest) {
        log.info("Inside GetFavoritesResult handleRequest");
        List<Destination> favorites = favoritesDao.getFavorites(getFavoritesRequest.getUserId());
        List<DestinationModel> destinationModels = new ModelConverter().toDestinationModelList(favorites);

        return GetFavoritesResult.builder()
                .withFavoriteModel(destinationModels)
                .build();
    }
}


