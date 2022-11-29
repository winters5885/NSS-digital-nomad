package com.nashss.se.digitalnomad.activity;

import com.nashss.se.digitalnomad.activity.requests.SaveFavoritesRequest;
import com.nashss.se.digitalnomad.activity.results.GetDestinationsResult;
import com.nashss.se.digitalnomad.activity.results.SaveFavoritesResult;
import com.nashss.se.digitalnomad.converters.ModelConverter;
import com.nashss.se.digitalnomad.dynamoDb.DestinationsDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.models.DestinationModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.List;


/**
 * Implementation of the GetDestinationsActivity for Digital Nomad's GetDestinations API.
 *
 * This API allows the customer to get a list of destinations.
 */
public class SaveFavoritesActivity {
    private final Logger log = LogManager.getLogger();
    private final DestinationsDao destinationsDao;

    /**
     * Instantiates a new GetDestinationsActivity object.
     *
     * @param destinationsDao DestinationsDao to access the destinations table.
     */
    @Inject
    public SaveFavoritesActivity(DestinationsDao destinationsDao) {
        this.destinationsDao = destinationsDao;
    }

    /**
     * This method handles the incoming request by retrieving the destinations from the database.
     * <p>
     * It then returns the destinations.
     * <p>
     *
     * @param saveFavoritesRequest request object containing the destination category
     * @return GetDestinationsResult result object
     */
    public SaveFavoritesResult handleRequest(final SaveFavoritesRequest saveFavoritesRequest) {
        log.info("Inside GetDestinationsResult handleRequest");
        List<Destination> destinations = destinationsDao.getDestinations(saveFavoritesRequest.getCategory());
        List<DestinationModel> destinationModels = new ModelConverter().toDestinationModelList(destinations);

        return SaveFavoritesResult.builder()
                .withDestinationList(destinationModels)
                .build();
    }
}

