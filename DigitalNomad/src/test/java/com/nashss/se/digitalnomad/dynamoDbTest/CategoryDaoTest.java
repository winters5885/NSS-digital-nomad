package com.nashss.se.digitalnomad.dynamoDbTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CategoryDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;
    private CategoryDao categoryDao;
    @Mock
    private PaginatedScanList<Category> scanResult;


    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryDao = new CategoryDao(dynamoDBMapper);
    }

    @Test
    void getCategory_scansDynamoDbCategories_returnsCategories() {
        // GIVEN
        when(dynamoDBMapper.scan(eq(Category.class), any())).thenReturn(scanResult);

        ArgumentCaptor<DynamoDBScanExpression> scanExpressionArgumentCaptor =
                ArgumentCaptor.forClass(DynamoDBScanExpression.class);
        
        // WHEN
        List<Category> categories = categoryDao.getCategories();

        // THEN
        verify(dynamoDBMapper).scan(eq(Category.class), scanExpressionArgumentCaptor.capture());
        DynamoDBScanExpression scanExpression = scanExpressionArgumentCaptor.getValue();

        verify(dynamoDBMapper).scan(Category.class, scanExpression);

        assertEquals(scanResult, categories, "Expected method to return the results of the scan");
    }
}
