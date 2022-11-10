package WebCrawler;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FoodieCrawler {

    public static void main(String[] args) throws IOException {

        CreateJsonObject jsonObj =  new CreateJsonObject();

        List<Destination> foodieList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://www.savoredjourneys.com/worlds-best-foodie-vacations/")
                .get();

        Elements foodieNameList = doc.getElementsByTag("h3");

        List<String> places = new ArrayList<>();
        for(int i = 0; i < 23; i++){
            System.out.println("Location: " + foodieNameList.get(i).html());

            if(foodieNameList.get(i).html().contains(".")) {
                places.add(foodieNameList.get(i).html());
            }
        }

        String city;
        String country;

        for(String place : places) {
            int cityStartIndex = place.indexOf(". ");
            int cityEndIndex = place.indexOf(",");

            city = place.substring(cityStartIndex+1, cityEndIndex);
            country = place.substring(cityEndIndex+1);

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            Destination foodieDestination = new Destination(city, country, "best_food", uuidAsString);
            foodieList.add(foodieDestination);
        }

        String jsonString = jsonObj.convertToJson(foodieList);

        System.out.println(jsonString);
        jsonObj.writeJsonToFile("foodieDestinations", jsonString);
    }
}
