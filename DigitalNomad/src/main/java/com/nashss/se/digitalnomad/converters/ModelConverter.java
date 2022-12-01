package com.nashss.se.digitalnomad.converters;

import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;
import com.nashss.se.digitalnomad.models.DestinationModel;
import com.nashss.se.digitalnomad.models.FavoriteModel;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Converts a list of Destinations to a list of DestinationModels.
     * @param destinations The Destinations to convert to DestinationModels
     * @return The converted list of DestinationModels
     */
    public List<DestinationModel> toDestinationModelList(List<Destination> destinations) {
        List<DestinationModel> destinationModels = new ArrayList<>();
        for (Destination dest : destinations) {
            destinationModels.add(toDestinationModel(dest));
        }
        return destinationModels;
    }

    /**
     * Converts a favorite to a FavoriteModel.
     * @param favorite The Favorites to convert to FavoriteModel
     * @return The converted favorite of FavoriteModel
     */
    public FavoriteModel toFavoriteModel(Favorite favorite) {
        return FavoriteModel.builder()
                .withUserId(favorite.getUserId())
                .withDestinations(favorite.getDestinations())
                .build();
    }
}
