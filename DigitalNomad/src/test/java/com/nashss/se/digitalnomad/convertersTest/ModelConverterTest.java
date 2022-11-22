package com.nashss.se.digitalnomad.convertersTest;

import com.nashss.se.digitalnomad.converters.ModelConverter;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.models.DestinationModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toDestinationModel_nullLocationName_convertsDestination() {
        Destination destination = new Destination();

        destination.setCity("Nashville");
        destination.setCountry("United States");
        destination.setLocationName(null);
        destination.setCategory("nightlife");
        destination.setDestinationID("1264f543ee911");

        DestinationModel destinationModel = modelConverter.toDestinationModel(destination);
        assertEquals(destination.getCity(), destinationModel.getCity());
        assertEquals(destination.getCountry(), destinationModel.getCountry());
        assertEquals(destination.getCategory(), destinationModel.getCategory());
        assertEquals(destination.getDestinationID(), destinationModel.getDestinationID());
        assertNull(destinationModel.getLocationName());
    }
}
