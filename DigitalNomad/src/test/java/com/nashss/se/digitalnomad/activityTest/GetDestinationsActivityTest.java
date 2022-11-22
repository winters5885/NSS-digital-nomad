package com.nashss.se.digitalnomad.activityTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
import com.nashss.se.digitalnomad.activity.GetCategoriesActivity;
import com.nashss.se.digitalnomad.activity.GetDestinationsActivity;
import com.nashss.se.digitalnomad.activity.requests.GetDestinationsRequest;
import com.nashss.se.digitalnomad.activity.results.GetCategoriesResult;
import com.nashss.se.digitalnomad.activity.results.GetDestinationsResult;
import com.nashss.se.digitalnomad.converters.ModelConverter;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.DestinationsDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.models.DestinationModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetDestinationsActivityTest {
    @Mock
    private DestinationsDao destinationsDao;

    private GetDestinationsActivity getDestinationsActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getDestinationsActivity = new GetDestinationsActivity(destinationsDao);
    }

    @Test
    public void handleRequest_destinationsFound_returnsListOfDestinations() {
        //GIVEN
        String expectedCity = "Nashville";
        String expectedCountry = "United States";
        String expectedLocationName = "NSS";
        String expectedCategory = "beach";
        String expectedDestinationID = "jfuf76411";

        List<Destination> destinationList = new ArrayList<>();
        Destination destination = new Destination();
        destination.setCity(expectedCity);
        destination.setCountry(expectedCountry);
        destination.setLocationName(expectedLocationName);
        destination.setCategory(expectedCategory);
        destination.setDestinationID(expectedDestinationID);

        destinationList.add(destination);

        when(destinationsDao.getDestinations(expectedCategory)).thenReturn(destinationList);

        GetDestinationsRequest request = GetDestinationsRequest.builder()
                .withCategory(expectedCategory)
                .build();
        //WHEN
        GetDestinationsResult result = getDestinationsActivity.handleRequest(request);


        //THEN
        assertEquals(expectedCategory, result.getDestinationList().get(0).getCategory());
        assertEquals(expectedCity, result.getDestinationList().get(0).getCity());
        assertEquals(expectedCountry, result.getDestinationList().get(0).getCountry());
        assertEquals(expectedLocationName, result.getDestinationList().get(0).getLocationName());
        assertEquals(expectedLocationName, result.getDestinationList().get(0).getLocationName());
    }
}
