package WebCrawler;

public class Destination {

    private String name;
    private String location;
    private String type;
    private String destinationID;

    Destination() {}
    Destination(String name, String location, String type, String destinationID) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.destinationID = destinationID;
    }


    public void setName(String Name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setDestinationID(String destinationID) {
        this.destinationID = destinationID;
    }
    public String getDestinationID() {
        return destinationID;
    }

}
