package com.nashss.se.web_crawler;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nashss.se.aws.dynamodb.DynamoDbClientProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class WalkableCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj = new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        List<Destination> walkableList = new ArrayList<>();
        String serializedDestinations = "";

        Document doc = Jsoup
                .connect("https://www.tripstodiscover.com/travel-by-foot-most-walkable-cities-around-the-world/")
                .get();

        Elements classList = doc.getElementsByClass("slide-title");
        Elements pList = classList.tagName("p");

        String city;
        String country;

        for(Element element : pList) {
            int cityStartIndex = element.html().indexOf(">");
            int cityEndIndex = element.html().indexOf(",");
            int countryEndIndex = element.html().indexOf("</a>");

            city = element.html().substring(cityStartIndex+1, cityEndIndex);
            country = element.html().substring(cityEndIndex+2, countryEndIndex);

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            Destination walkableDestination = new Destination(city, country, null, "most_walkable", uuidAsString);
            walkableList.add(walkableDestination);
        }

        serializedDestinations = objectMapper.writeValueAsString(walkableList);
        jsonObj.writeJsonToFile("walkableDestinations", serializedDestinations);

        List<Destination> deserializedDestObjects = objectMapper.readValue(serializedDestinations, new TypeReference<List<Destination>>() {
        });

        for (Destination dest : deserializedDestObjects) {
            DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
            mapper.save(dest);
        }
    }
}
