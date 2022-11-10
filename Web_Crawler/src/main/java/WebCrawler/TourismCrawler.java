package WebCrawler;

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

        List<Destination> tourismList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://www.planetware.com/world/top-rated-tourist-attractions-in-the-world-cam-1-40.htm")
                .get();

        Elements tourismNameList = doc.getElementsByClass("sitename");

        String place;
        String country;

        for(Element element : tourismNameList) {
            int placeStartIndex = element.html().indexOf(".");
            int placeEndIndex = element.html().indexOf(",");

            if(element.html().contains(",")) {
                place = element.html().substring(placeStartIndex+1, placeEndIndex);
                country = element.html().substring(placeEndIndex+2);

                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                Destination tourismDestination = new Destination(place, country, "best_tourism", uuidAsString);
                tourismList.add(tourismDestination);
            }
        }

        String jsonString = jsonObj.convertToJson(tourismList);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);
    }
}
