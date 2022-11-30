package com.nashss.se.digitalnomad.activityTest;

import com.nashss.se.digitalnomad.activity.SaveFavoritesActivity;
import com.nashss.se.digitalnomad.activity.requests.SaveFavoritesRequest;
import com.nashss.se.digitalnomad.activity.results.SaveFavoritesResult;
import com.nashss.se.digitalnomad.dynamoDb.FavoritesDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class SaveFavoritesActivityTest {
    @Mock
    private FavoritesDao favoritesDao;

    private SaveFavoritesActivity saveFavoritesActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        saveFavoritesActivity = new SaveFavoritesActivity(favoritesDao);
    }

    @Test
    public void handleRequest_validRequest_returnsValidSaveFavoritesActivity() {
        //GIVEN
        String userId = "testUser";
        List<String> favoriteDestinations = new ArrayList<>();
        String destination1 = "destination1";
        String destination2 = "destination2";
        favoriteDestinations.add(destination1);
        favoriteDestinations.add(destination2);

        Favorite favorite = new Favorite(userId, favoriteDestinations);

        SaveFavoritesRequest saveFavoritesRequest = new SaveFavoritesRequest(favoriteDestinations);

        when(favoritesDao.saveFavorites(favorite)).thenReturn(favorite);

        //WHEN
        SaveFavoritesResult result = saveFavoritesActivity.handleRequest(saveFavoritesRequest);


        //THEN
        assertEquals(favoriteDestinations, result.getFavoriteModel().getDestinations());

    }
}
