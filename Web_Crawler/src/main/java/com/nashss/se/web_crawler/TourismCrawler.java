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

public class TourismCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj = new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        List<DestinationModel> tourismList = new ArrayList<>();
        String serializedDestinations = "";

        Document doc = Jsoup
                .connect("https://www.planetware.com/world/top-rated-tourist-attractions-in-the-world-cam-1-40.htm")
                .get();

        Elements tourismNameList = doc.getElementsByClass("sitename");

        String locationName;
        String country;

        for(Element element : tourismNameList) {
            int placeStartIndex = element.html().indexOf(".");
            int placeEndIndex = element.html().indexOf(",");

            if(element.html().contains(",")) {
                locationName = element.html().substring(placeStartIndex+1, placeEndIndex);
                country = element.html().substring(placeEndIndex+2);

                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                DestinationModel tourismDestination = new DestinationModel(null, country, locationName,"best_tourism", uuidAsString);
                tourismList.add(tourismDestination);
            }
        }

        serializedDestinations = objectMapper.writeValueAsString(tourismList);
        jsonObj.writeJsonToFile("tourismDestinations", serializedDestinations);

        List<DestinationModel> deserializedDestObjects = objectMapper.readValue(serializedDestinations, new TypeReference<List<DestinationModel>>() {
        });

        for (DestinationModel dest : deserializedDestObjects) {
            DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
            mapper.save(dest);
        }
    }
}
