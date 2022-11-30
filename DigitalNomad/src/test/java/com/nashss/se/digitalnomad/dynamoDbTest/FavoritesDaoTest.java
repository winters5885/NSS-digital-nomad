package com.nashss.se.digitalnomad.dynamoDbTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.nashss.se.digitalnomad.dynamoDb.FavoritesDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
import com.nashss.se.digitalnomad.dynamoDb.models.Favorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class FavoritesDaoTest {

    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private FavoritesDao favoritesDao;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        favoritesDao = new FavoritesDao(dynamoDBMapper);
    }

    @Test
    void saveFavorites_validSaveFavoritesRequest_returnsSaveFavoritesRequest() {
        //GIVEN
        String userId = "testUser";
        List<String> favoriteDestinations = new ArrayList<>();
        String destination1 = "destination1";
        String destination2 = "destination2";
        favoriteDestinations.add(destination1);
        favoriteDestinations.add(destination2);

        Favorite favorite = new Favorite(userId, favoriteDestinations);

        when(dynamoDBMapper.save(eq(Favorite.class)).thenReturn(favorite);


//
//
//        ArgumentCaptor<DynamoDBQueryExpression> queryExpressionArgumentCaptor =
//                ArgumentCaptor.forClass(DynamoDBQueryExpression.class);

        // WHEN
       Favorite result = favoritesDao.saveFavorites(favorite);

        // THEN
//        verify(dynamoDBMapper).query(eq(Destination.class), queryExpressionArgumentCaptor.capture());
//        DynamoDBQueryExpression queryExpression = queryExpressionArgumentCaptor.getValue();
//
//        verify(dynamoDBMapper).query(Destination.class, queryExpression);


        verify(dynamoDBMapper).save(eq(Favorite.class));
//        assertEquals(queryResult, destinations, "Expected method to return the results of the query");
    }
}
