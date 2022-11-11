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

        List<Destination> mountainList = new ArrayList<>();

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
            String city = null;
            String country = locationList.get(i-1).html();

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            Destination mountainDest = new Destination(null, country, mountainName, "mountain", uuidAsString);
            mountainList.add(mountainDest);
        }

        String jsonString = jsonObj.convertToJson(mountainList);
        System.out.println(jsonString);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);

        List<Destination> deserializedDestObjects = objectMapper.readValue(jsonString, new TypeReference<List<Destination>>(){});

        DynamoDBMapper mapper = new DynamoDBMapper(DynamoDbClientProvider.getDynamoDBClient());
        mapper.save(deserializedDestObjects);
    }
}
