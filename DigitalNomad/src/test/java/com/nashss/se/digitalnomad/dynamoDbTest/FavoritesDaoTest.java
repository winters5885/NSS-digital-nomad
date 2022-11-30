//package com.nashss.se.digitalnomad.dynamoDbTest;
//
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
//import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;
//import com.nashss.se.digitalnomad.dynamoDb.DestinationsDao;
//import com.nashss.se.digitalnomad.dynamoDb.models.Destination;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.eq;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//public class FavoritesDaoTest {
//
//    @Mock
//    private DynamoDBMapper dynamoDBMapper;
//    private DestinationsDao destinationsDao;
//
//    @Mock
//    private PaginatedQueryList<Destination> queryResult;
//
//
//    @BeforeEach
//    public void setup() {
//        MockitoAnnotations.openMocks(this);
//        destinationsDao = new DestinationsDao(dynamoDBMapper);
//    }
//
//    @Test
//    void getDestinations_QueriesDynamoDbDestinationsTable_returnsDestinations() {
//        //GIVEN
//        String category = "nightlife";
//        when(dynamoDBMapper.query(eq(Destination.class), any())).thenReturn(queryResult);
//
//        ArgumentCaptor<DynamoDBQueryExpression> queryExpressionArgumentCaptor =
//                ArgumentCaptor.forClass(DynamoDBQueryExpression.class);
//
//        // WHEN
//        List<Destination> destinations = destinationsDao.getDestinations(category);
//
//        // THEN
//        verify(dynamoDBMapper).query(eq(Destination.class), queryExpressionArgumentCaptor.capture());
//        DynamoDBQueryExpression queryExpression = queryExpressionArgumentCaptor.getValue();
//
//        verify(dynamoDBMapper).query(Destination.class, queryExpression);
//
//        assertEquals(queryResult, destinations, "Expected method to return the results of the query");
//    }
//}
