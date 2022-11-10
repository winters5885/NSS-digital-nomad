package WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MostPopularCitiesCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj =  new CreateJsonObject();

        List<Destination> mostPopularList = new ArrayList<>();


        Document doc = Jsoup
                .connect("https://govisity.com/most-visited-cities-in-the-world/")
                .get();

        Elements popularNameList = doc.getElementsByTag("h3");

        String city;
        String country;

        for(int i = 2; i < 52; i++) {
            int cityStartIndex = popularNameList.get(i).html().indexOf(". ");
            int cityEndIndex = popularNameList.get(i).html().indexOf(",");
            int countryStartIndex = popularNameList.get(i).html().indexOf(", ");

            city = popularNameList.get(i).html().substring(cityStartIndex+1, cityEndIndex);
            country = popularNameList.get(i).html().substring(countryStartIndex+2);

            int countryEndIndex = country.indexOf("(");
            country = country.substring(0, countryEndIndex);

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            Destination popularDestination = new Destination(city, country, "most_popular", uuidAsString);
            mostPopularList.add(popularDestination);
        }

        String jsonString = jsonObj.convertToJson(mostPopularList);
        jsonObj.writeJsonToFile("mostPopularDestinations", jsonString);
    }
}
