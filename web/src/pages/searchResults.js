import DigitalNomadClient from "../api/digitalNomadClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SearchResults extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['populateResultsList', 'displayCategory','mount'], this);
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
    //    var categoryChosen = ["Beaches"];
    //    var categoryDisplayed = [];

    //    for (var i = 0; i < categoryChosen.length; i++){
    //     categoryDisplayed.push("<p>" + categoryChosen[i] + "</p>");
    //    }
    //    document.getElementById("category").innerHTML = categoryDisplayed.join("");
        //Use category from query to set value
        var categoryChosen = "Beaches";
        document.getElementById("category").innerHTML = categoryChosen;
    }

    async populateResultsList() {

        var destinations = ["Thailand, Koh Lanta", "Kenya, Diani Beach", "Australia, Byron"];
        var destinationResults = [];

        for (var i = 0; i < destinations.length; i++) {
            destinationResults.push("<li>" + destinations[i] + "</li>");
     }
     document.getElementById("resultsList").innerHTML = destinationResults.join("");

    }

    
    
    
    
    


}

const main = async () => {
    const results = new SearchResults();
    results.mount();
};

window.addEventListener('DOMContentLoaded', main);