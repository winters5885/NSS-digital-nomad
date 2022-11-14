package com.nashss.se.digitalnomad.dynamoDb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Set;

public class Favorites {

    @DynamoDBTable(tableName = "favorites")
    public class Favorite {

        private String userId;
        private Set<Destination> destinations;


        public Favorite() {
        }

        public Favorite(String userId, Set<Destination> destinations) {
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
        public Set<Destination> getDestinations() {
            return destinations;
        }

        public void setDestinations(Set<Destination> destinations) {
            this.destinations = destinations;
        }

    }
}
