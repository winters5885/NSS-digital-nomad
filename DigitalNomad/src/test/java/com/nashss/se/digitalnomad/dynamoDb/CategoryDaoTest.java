package com.nashss.se.digitalnomad.dynamoDb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedScanList;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class CategoryDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    private CategoryDao categoryDao;

    @Mock
    private PaginatedScanList paginatedScanList;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        categoryDao = new CategoryDao(dynamoDBMapper);
    }

    @Test
    void getCategory_scansDynamoDbCategories_returnsCategories() {
        // GIVEN
        paginatedScanList = new PaginatedScanList<>(dynamoDBMapper, );
        Category category1 = new Category("beach");
        Category category2 = new Category("city");
        Category category3 = new Category("best food");
        Category category4 = new Category("most popular");
        Category category5 = new Category("mountain");
        Category category6 = new Category("nightlife");
        Category category7 = new Category("best tourism");
        Category category8 = new Category("most walkable");

        categories.add(category1);
        categories.add(category2);
        categories.add(category3);
        categories.add(category4);
        categories.add(category5);
        categories.add(category6);
        categories.add(category7);
        categories.add(category8);

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        when(dynamoDBMapper.scan(Category.class, scanExpression)).thenReturn(categories);

        // WHEN
        List<Category> result = categoryDao.getCategories();

        // THEN
        verify(dynamoDBMapper).scan(Category.class, scanExpression);
        assertEquals(categories, result,
                String.format("Expected to receive categories returned by DDB (%s), but received %s",
                        categories,
                        result)
        );
    }
}
