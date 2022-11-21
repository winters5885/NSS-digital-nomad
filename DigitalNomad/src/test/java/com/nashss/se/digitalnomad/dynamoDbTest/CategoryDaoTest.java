package com.nashss.se.digitalnomad.dynamoDbTest;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
        Category category1 = new Category("beach");
        Category category2 = new Category("city");
        Category category3 = new Category("best food");
        Category category4 = new Category("most popular");
        Category category5 = new Category("mountain");
        Category category6 = new Category("nightlife");
        Category category7 = new Category("best tourism");
        Category category8 = new Category("most walkable");

        scanResult.add(category1);
        scanResult.add(category2);
        scanResult.add(category3);
        scanResult.add(category4);
        scanResult.add(category5);
        scanResult.add(category6);
        scanResult.add(category7);
        scanResult.add(category8);


        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        when(dynamoDBMapper.scan(Category.class, scanExpression)).thenReturn(scanResult);
        when(categoryDao.getCategories()).thenReturn(scanResult);

        // WHEN
        List<Category> categories = categoryDao.getCategories();

        // THEN
        verify(dynamoDBMapper).scan(Category.class, scanExpression);

        assertEquals(scanResult, categories,
                String.format("Expected to receive categories returned by DDB (%s), but received %s",
                        scanResult,
                        categories)
        );
    }
}
