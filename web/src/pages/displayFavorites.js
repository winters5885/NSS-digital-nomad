import DigitalNomadClient from "../api/digitalNomadClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class DisplayFavorites extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new DigitalNomadClient();
        this.getFavorites();
    }

    async getFavorites() {
        const urlParams = new URLSearchParams(window.location.search);
        const userIdFromURL = urlParams.get('userId');
        console.log("userIdFromURL: " + userIdFromURL);


        document.getElementById("theURL").innerHTML += "<br>" + "http://localhost:8000/favorites.html?userId=" +
                        userIdFromURL + "</br>";


        const jsonList = await this.client.getFavorites(userIdFromURL);
        console.log("jsonList: " + jsonList);

        if (jsonList.length == 0) {
            document.getElementById("favoritesList").innerHTML = "Return list is empty."
        }

        for (var i = 0; i < jsonList.length; i++) {
        
            var destination = jsonList[i];

            if (destination.city != null) {
                document.getElementById("favoritesList").innerHTML += "<br>"+ destination.city + ", " + 
                destination.country +"</br>";
            }
            else if (destination.locationName != null) {
                document.getElementById("favoritesList").innerHTML += "<br>" + destination.locationName + ", " + 
                destination.country + "</br>";
            }
        }
    }
}

const main = async () => {
    const favorites = new DisplayFavorites();
    favorites.mount();
};

window.addEventListener('DOMContentLoaded', main);
