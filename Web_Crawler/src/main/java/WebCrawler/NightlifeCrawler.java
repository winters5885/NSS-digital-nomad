package WebCrawler;

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

        List<Destination> nightLifeList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://www.nomadicmatt.com/travel-blogs/the-saturday-city-top-ten-cities-for-partying/")
                .get();

        Elements nightLifeNameList = doc.getElementsByTag("h3");

        String city;
        String country;

        for(int i = 0; i < 10; i++) {
            String location = nightLifeNameList.get(i).html();

            int cityStartIndex = location.indexOf(". ");
            int cityEndIndex = location.indexOf(",");

            if(location.contains(".")) {
                city = location.substring(cityStartIndex+1, cityEndIndex);
                country = location.substring(cityEndIndex+2);

                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                Destination nightlifeDestination = new Destination(city, country, "nightlife", uuidAsString);
                nightLifeList.add(nightlifeDestination);
            }
        }

        String jsonString = jsonObj.convertToJson(nightLifeList);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);
    }
}
