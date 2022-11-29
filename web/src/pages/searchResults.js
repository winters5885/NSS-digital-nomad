import DigitalNomadClient from "../api/digitalNomadClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SearchResults extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['displayCategory', 'populateResultsList', 'mount'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new DigitalNomadClient();
        this.populateResultsList();
        this.displayCategory();
    }

    async displayCategory() {
        const urlParams = new URLSearchParams(window.location.search);

        var categoryChosen = urlParams.get('categoryId');
        document.getElementById("category").innerHTML = categoryChosen;
    }

    async populateResultsList() {
        const urlParams = new URLSearchParams(window.location.search);
        const categoryIdFromURL = urlParams.get('categoryId');

        const jsonList = await this.client.getDestinationResultsList(categoryIdFromURL);

        var destinationResults = [];

        for (var i = 0; i < jsonList.length; i++) {
            var destinationToString = JSON.stringify(jsonList[i]);
            var destination = JSON.parse(destinationToString);
            destinationResults.push("<li>" + destination + "</li>");

            if (destination.city != null) {
            document.getElementById("resultsList").innerHTML += "<br>"+ destination.city + ", " + 
            destination.country +"</br>";
            }
            else if (destination.locationName != null) {
                document.getElementById("resultsList").innerHTML += "<br>" + destination.locationName + ", " + 
                destination.country + "</br>";
            }
     }
    }

    
    
    
    
    


}

const main = async () => {
    const results = new SearchResults();
    results.mount();
};

window.addEventListener('DOMContentLoaded', main);