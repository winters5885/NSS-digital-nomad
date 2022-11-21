package com.nashss.se.digitalnomad.activity;

import com.nashss.se.digitalnomad.activity.results.GetCategoriesResult;
import com.nashss.se.digitalnomad.activity.results.GetDestinationsResult;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.DestinationsDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

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
     * @return GetDestinationsResult result object
     */
    public GetDestinationsResult handleRequest(String category) {
        log.info("Inside GetDestinationsResult handleRequest");
        List<Destination> destinations = destinationsDao.getDestinations(category);

        return GetDestinationsResult.builder()
                .withCategory(category)
                .build();
    }
}

