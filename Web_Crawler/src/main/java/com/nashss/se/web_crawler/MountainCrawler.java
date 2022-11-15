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

public class MountainCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj = new CreateJsonObject();
        ObjectMapper objectMapper = new ObjectMapper();

        List<DestinationModel> mountainList = new ArrayList<>();
        String serializedDestinations = "";

        Document doc = Jsoup
                .connect("https://wanderlustphotosblog.com/2020/05/16/the-top-20-mountain-travel-destinations-in-the-world/")
                .get();

        Elements mountainNameList = doc.getElementsByTag("h2");
        Elements locationList = doc.getElementsByTag("h3");

        String mountainName = "";

        for (int i = 1; i < 21; i++) {
            if(mountainNameList.get(i).html().contains(".")) {
                int startIndex = mountainNameList.get(i).html().indexOf(". ");
                mountainName = mountainNameList.get(i).html().substring(startIndex+1);
                
                if(mountainName.contains("; ")) {
                    int index = mountainName.indexOf("; ");
                    mountainName = mountainName.substring(index+1);
                }
            }

            String country = locationList.get(i-1).html();

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            DestinationModel mountainDest = new DestinationModel(null, country, mountainName, "mountain", uuidAsString);
            mountainList.add(mountainDest);
        }

        serializedDestinations = objectMapper.writeValueAsString(mountainList);
        jsonObj.writeJsonToFile("mountainDestinations", serializedDestinations);

        List<DestinationModel> deserializedDestObjects = objectMapper.readValue(serializedDestinations, new TypeReference<List<DestinationModel>>() {
        });

        for (DestinationModel dest : deserializedDestObjects) {
            DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
            mapper.save(dest);
        }
    }
}
