package com.nashss.se.digitalnomad.activityTest;

import com.nashss.se.digitalnomad.activity.GetCategoriesActivity;
import com.nashss.se.digitalnomad.activity.results.GetCategoriesResult;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetCategoriesActivityTest {

    @Mock
    private CategoryDao categoryDao;

    private GetCategoriesActivity getCategoriesActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getCategoriesActivity = new GetCategoriesActivity(categoryDao);
    }

    @Test
    public void handleRequest_categoriesFound_returnsListOfCategories() {
        //GIVEN
        Category catOne = new Category("catOne");
        Category catTwo = new Category("catTwo");
        Category catThree = new Category("catThree");

        List<Category> categoryList = new ArrayList<>();
        categoryList.add(catOne);
        categoryList.add(catTwo);
        categoryList.add(catThree);

        when(categoryDao.getCategories()).thenReturn(categoryList);

        String testString = "[catOne, catTwo, catThree]";

        //WHEN calling the GetCategoriesActivity handleRequest() method
        GetCategoriesResult result =  getCategoriesActivity.handleRequest();

        //THEN
        assertEquals(3, result.getCategoriesList().size());
        assertEquals(testString , result.getCategoriesList().toString());
    }

}
