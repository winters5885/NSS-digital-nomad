package com.nashss.se.digitalnomad.activity;

import com.nashss.se.digitalnomad.activity.results.GetCategoriesResult;
import com.nashss.se.digitalnomad.dynamoDb.CategoryDao;
import com.nashss.se.digitalnomad.dynamoDb.models.Category;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

/**
 * Implementation of the GetCategoriesActivity for Digital Nomad's GetCategories API.
 *
 * This API allows the customer to get their saved categories.
 */
public class GetCategoriesActivity {
    private final Logger log = LogManager.getLogger();
    private final CategoryDao categoryDao;

    /**
     * Instantiates a new GetCategoriesActivity object.
     *
     * @param categoryDao CategoryDao to access the categories table.
     */
    @Inject
    public GetCategoriesActivity(CategoryDao categoryDao) {
        this.categoryDao = categoryDao;
    }

    /**
     * This method handles the incoming request by retrieving the categories from the database.
     * <p>
     * It then returns the categories.
     * <p>
     * If the category does not exist, this should throw a CategoryNotFoundException.
     *
     * @return GetCategoriesResult result object
     */
    public GetCategoriesResult handleRequest() {
        log.info("Inside GetCategoriesResult handleRequest");
        List<Category> categories = categoryDao.getCategories();
        List<String> categoriesList = new ArrayList<>();

        for (Category category : categories) {
            categoriesList.add(category.getCategory());
        }
        return GetCategoriesResult.builder()
                .withCategory(categoriesList)
                .build();
    }
}
