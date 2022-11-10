package WebCrawler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CityCrawler {

    public static void main(String[] args) throws IOException {
        CreateJsonObject jsonObj =  new CreateJsonObject();

        String cityName;
        String countryName;

        List<Destination> cityList = new ArrayList<>();

        Document doc = Jsoup
                .connect("https://www.holidify.com/pages/best-urban-city-experience-1534.html\n")
                .get();

        Elements cityNameList = doc.getElementsByTag("h2");

        for(Element element : cityNameList) {
            if(element.html().contains(". ")) {
                int startIndex = element.html().indexOf(". ");
                int cityEndIndex = element.html().indexOf(" (");
                int countryEndIndex = element.html().indexOf(")");

                cityName = element.html().substring(startIndex+1, cityEndIndex);
                countryName = element.html().substring(cityEndIndex+2, countryEndIndex);

                UUID uuid = UUID.randomUUID();
                String uuidAsString = uuid.toString();

                Destination cityDestination = new Destination(cityName, countryName, "city", uuidAsString);
                cityList.add(cityDestination);
            }
        }

        String jsonString = jsonObj.convertToJson(cityList);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);
    }
}
