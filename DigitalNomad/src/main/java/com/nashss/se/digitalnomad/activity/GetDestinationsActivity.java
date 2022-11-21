package com.nashss.se.digitalnomad.activity;

import com.nashss.se.digitalnomad.activity.requests.GetDestinationsRequest;
import com.nashss.se.digitalnomad.activity.results.GetDestinationsResult;
import com.nashss.se.digitalnomad.converters.ModelConverter;
import com.nashss.se.digitalnomad.dynamoDb.DestinationsDao;
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
public class GetDestinationsActivity {
    private final Logger log = LogManager.getLogger();
    private final DestinationsDao destinationsDao;

    /**
     * Instantiates a new GetDestinationsActivity object.
     *
     * @param destinationsDao DestinationsDao to access the destinations table.
     */
    @Inject
    public GetDestinationsActivity(DestinationsDao destinationsDao) {
        this.destinationsDao = destinationsDao;
    }

    /**
     * This method handles the incoming request by retrieving the destinations from the database.
     * <p>
     * It then returns the destinations.
     * <p>
     * If the destinations do not exist, this should throw a DestinationsNotFoundException.
     *
     * @param getDestinationsRequest request object containing the destination category
     * @return GetDestinationsResult result object
     */
    public GetDestinationsResult handleRequest(final GetDestinationsRequest getDestinationsRequest) {
        log.info("Inside GetDestinationsResult handleRequest");
        List<Destination> destinations = destinationsDao.getDestinations(getDestinationsRequest.getCategory());
        List<DestinationModel> destinationModels = new ModelConverter().toDestinationModelList(destinations);

        return GetDestinationsResult.builder()
                .withDestinationList(destinationModels)
                .build();
    }
}

