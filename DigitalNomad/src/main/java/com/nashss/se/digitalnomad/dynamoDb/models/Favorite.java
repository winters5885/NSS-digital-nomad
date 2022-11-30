package com.nashss.se.digitalnomad.dynamoDb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;

@DynamoDBTable(tableName = "favorites")
public class Favorite {

    private String userId;
    private List<String> destinations;

    /**
     * Empty constructor for Category POJO.
     */
    public Favorite() {
    }

    /**
     * Constructor with parameters for Category POJO.
     * @param userId for user ID
     * @param destinations A set of destinations for this specific Favorite instance
     */
    public Favorite(String userId, List<String> destinations) {
        this.userId = userId;
        this.destinations = destinations;
    }

    @DynamoDBHashKey(attributeName = "userId")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @DynamoDBAttribute(attributeName = "destinations")
    public List<String> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<String> destinations) {
        this.destinations = destinations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Favorite)) {
            return false;
        }
        Favorite favorite = (Favorite) o;
        return Objects.equals(userId, favorite.userId) && Objects.equals(destinations, favorite.destinations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, destinations);
    }

    @Override
    public String toString() {
        return "Favorite{" +
                "userId='" + userId + '\'' +
                ", destinations=" + destinations +
                '}';
    }
}

