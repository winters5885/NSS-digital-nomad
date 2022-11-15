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

public class BeachCrawler {

    public static void main(String[] args) throws IOException {
        CreateJsonObject jsonObj = new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        DestinationModel destination = new DestinationModel();
        List<DestinationModel> destinationList = new ArrayList<>();
        String serializedDestinations = "";

        Document doc = Jsoup
                .connect("https://viatravelers.com/best-beach-vacation-spots/")
                .get();

        Elements list = doc.getElementsByTag("h3");

        for (Element element : list) {
            if (element.html().contains(".")) {

                int beachStartIndex = element.html().indexOf(".");
                int beachEndIndex = element.html().indexOf(",");
                int locationEndIndex = element.html().indexOf("</");

                String locationName = element.html().substring(beachStartIndex + 2, beachEndIndex);
                String country = element.html().substring(beachEndIndex + 1, locationEndIndex);

                String uuid = UUID.randomUUID().toString();

                destination = new DestinationModel(null, country, locationName, "beach", uuid);
                destinationList.add(destination);
            }
        }

        serializedDestinations = objectMapper.writeValueAsString(destinationList);
        jsonObj.writeJsonToFile("beachDestinations", serializedDestinations);

        List<DestinationModel> deserializedDestObjects = objectMapper.readValue(serializedDestinations, new TypeReference<List<DestinationModel>>() {
        });

        for (DestinationModel dest : deserializedDestObjects) {
            DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
            mapper.save(dest);
        }
    }
}
