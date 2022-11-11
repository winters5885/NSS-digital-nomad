package com.nashss.se.web_crawler;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.aws.dynamodb.DynamoDbClientProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodieCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj =  new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Destination> foodieList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://www.savoredjourneys.com/worlds-best-foodie-vacations/")
                .get();

        Elements foodieNameList = doc.getElementsByTag("h3");

        List<String> places = new ArrayList<>();
        for(int i = 0; i < 23; i++){
            if(foodieNameList.get(i).html().contains(".")) {
                places.add(foodieNameList.get(i).html());
            }
        }

        String city;
        String country;

        for(String place : places) {
            int cityStartIndex = place.indexOf(". ");
            int cityEndIndex = place.indexOf(",");

            city = place.substring(cityStartIndex+1, cityEndIndex);
            country = place.substring(cityEndIndex+1);

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            String locationName = null;

            Destination foodieDestination = new Destination(city, country, null, "best_food", uuidAsString);
            foodieList.add(foodieDestination);
        }

        String jsonString = jsonObj.convertToJson(foodieList);
        System.out.println(jsonString);
        jsonObj.writeJsonToFile("foodieDestinations", jsonString);

        List<Destination> deserializedDestObjects = objectMapper.readValue(jsonString, new TypeReference<List<Destination>>(){});

        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        mapper.save(deserializedDestObjects);
    }
}
