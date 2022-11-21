package com.nashss.se.digitalnomad.converters;

import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.models.DestinationModel;

public class ModelConverter {
    /**
     * Converts a provided {@link Destination} into a {@link DestinationModel} representation.
     * @param destination the destination to convert
     * @return the converted destination
     */
    public DestinationModel toDestinationModel(Destination destination) {

        return DestinationModel.builder()
                .withCity(destination.getCity())
                .withCountry(destination.getCountry())
                .withLocationName(destination.getLocationName())
                .withCategory(destination.getCategory())
                .withDestinationID(destination.getDestinationID())
                .build();
    }
}
