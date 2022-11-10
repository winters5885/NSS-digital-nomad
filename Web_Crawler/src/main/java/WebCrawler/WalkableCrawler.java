package WebCrawler;

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

        List<Destination> walkableList = new ArrayList<>();

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

            Destination walkableDestination = new Destination(city, country, "most_walkable", uuidAsString);
            walkableList.add(walkableDestination);
        }

        String jsonString = jsonObj.convertToJson(walkableList);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);
    }
}
