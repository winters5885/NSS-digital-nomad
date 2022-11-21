package com.nashss.se.digitalnomad.dynamoDb;

import com.nashss.se.digitalnomad.dynamoDb.models.Category;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import javax.inject.Inject;


public class CategoryDao {

    private final DynamoDBMapper dynamoDbMapper;
    private final Logger log = LogManager.getLogger();

    /**
     * Instantiates an CategoryDao object.
     *
     * @param dynamoDbMapper the {@link DynamoDBMapper} used to interact with the categories table
     */
    @Inject
    public CategoryDao(DynamoDBMapper dynamoDbMapper) {
        this.dynamoDbMapper = dynamoDbMapper;
    }

    /**
     * Retrieves all categories in categories table.
     * <p>
     * If not found, throws CategoryNotFoundException.
     *
     * @return All categories in categories table
     */
    public List<Category> getCategories() {
        log.info("Inside CategoryDao getCategory");

        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();
        return dynamoDbMapper.scan(Category.class, scanExpression);
    }
}

