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

public class NightlifeCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj = new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Destination> nightLifeList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://www.nomadicmatt.com/travel-blogs/the-saturday-city-top-ten-cities-for-partying/")
                .get();

        Elements nightLifeNameList = doc.getElementsByTag("h3");

        String city;
        String country;
        String locationName = null;

        for(int i = 0; i < 10; i++) {
            String location = nightLifeNameList.get(i).html();

            int cityStartIndex = location.indexOf(". ");
            int cityEndIndex = location.indexOf(",");

            if(location.contains(".")) {
                city = location.substring(cityStartIndex+1, cityEndIndex);
                country = location.substring(cityEndIndex+2);

                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                Destination nightlifeDestination = new Destination(city, country, null, "nightlife", uuidAsString);
                nightLifeList.add(nightlifeDestination);
            }
        }

        String jsonString = jsonObj.convertToJson(nightLifeList);
        System.out.println(jsonString);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);

        List<Destination> deserializedDestObjects = objectMapper.readValue(jsonString, new TypeReference<List<Destination>>(){});

        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        mapper.save(deserializedDestObjects);
    }
}
