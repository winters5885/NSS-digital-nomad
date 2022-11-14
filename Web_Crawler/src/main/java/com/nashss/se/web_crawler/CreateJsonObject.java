package com.nashss.se.web_crawler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class CreateJsonObject {
    public String convertToJson(List<Destination> destinationList) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(destinationList);
    }

    public void writeJsonToFile(String filename, String jsonString) {
        File myFile = new File("/Users/nss_student/workspace/u5-project-smashing-pumpkin-pie/Web_Crawler/src/main/Java/com/nashss/se/web_crawler/Json/"
                + filename + ".json");

        try {
            FileWriter destinationFile = new FileWriter(myFile);
            destinationFile.write(jsonString);
            destinationFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
