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
    //    var categoryChosen = ["Beaches"];
    //    var categoryDisplayed = [];

    //    for (var i = 0; i < categoryChosen.length; i++){
    //     categoryDisplayed.push("<p>" + categoryChosen[i] + "</p>");
    //    }
    //    document.getElementById("category").innerHTML = categoryDisplayed.join("");
        //Use category from query to set value
        const urlParams = new URLSearchParams(window.location.search);

        var categoryChosen = urlParams.get('categoryId');
        document.getElementById("category").innerHTML = categoryChosen;
    }

    async populateResultsList() {
        const urlParams = new URLSearchParams(window.location.search);
        const categoryIdFromURL = urlParams.get('categoryId');

        const jsonList = await this.client.getDestinationResultsList(categoryIdFromURL);

        console.log("this is my json list: ", jsonList);

        //var destinations = ["Thailand, Koh Lanta", "Kenya, Diani Beach", "Australia, Byron"];

        //var destinationResults = [];

        if (jsonList == null) {
            return;
        }

        let destinationHtml = '';
        let destination;
        for (destination of jsonList) {


            destinationHtml += `

                <li class="destination">
                    <span class="locationName">${destination.locationName}</span>
                    in
                    <span class="cityName">${destination.city}</span>
                    ,
                    <span class="countryName">${destination.country}</span>
                </li>
                `;
        }
    


            //destinationResults.push("<li>" + item + "</li>");
     
     document.getElementById("resultsList").innerHTML = destinationHtml; //destinationResults.join("");

    }

    
    
    
    
    


}

const main = async () => {
    const results = new SearchResults();
    results.mount();
};

window.addEventListener('DOMContentLoaded', main);