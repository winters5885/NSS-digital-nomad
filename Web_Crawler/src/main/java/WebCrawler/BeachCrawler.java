package WebCrawler;

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
        CreateJsonObject jsonObj =  new CreateJsonObject();

        List<Destination> destinationList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://viatravelers.com/best-beach-vacation-spots/")
                .get();

        Elements list = doc.getElementsByTag("h3");

        for(Element element : list) {
            if(element.html().contains(".")) {

                int beachStartIndex = element.html().indexOf(".");
                int beachEndIndex = element.html().indexOf(",");
                int locationEndIndex = element.html().indexOf("</");

                String beach = element.html().substring(beachStartIndex+1, beachEndIndex);
                String location = element.html().substring(beachEndIndex+1, locationEndIndex);

                String uuid = UUID.randomUUID().toString();

                Destination destination = new Destination(beach, location, "beach", uuid);
                destinationList.add(destination);
            }
        }

        String jsonString = jsonObj.convertToJson(destinationList);

        System.out.println(jsonString);
        jsonObj.writeJsonToFile("beachDestinations", jsonString);
    }
}
