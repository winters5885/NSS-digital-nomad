package WebCrawler;

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
            String location = locationList.get(i-1).html();

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();

            Destination mountainDest = new Destination(mountainName, location, "mountain", uuidAsString);
            mountainList.add(mountainDest);
        }

        String jsonString = jsonObj.convertToJson(mountainList);
        jsonObj.writeJsonToFile("cityDestinations", jsonString);
    }
}
