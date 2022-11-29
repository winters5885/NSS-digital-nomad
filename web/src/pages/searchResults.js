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
        var categoryList = await this.client.getCategoriesList();

        var categoryChosen = urlParams.get('categoryId');
        
        if (!categoryList.includes(categoryChosen)) {
            document.getElementById("category").innerHTML = "Not a valid category.";
        } else {
            document.getElementById("category").innerHTML = categoryChosen;
        }
    }

    async populateResultsList() {
        const urlParams = new URLSearchParams(window.location.search);
        const categoryIdFromURL = urlParams.get('categoryId');
        
        const jsonList = await this.client.getDestinationResultsList(categoryIdFromURL);

        for (var i = 0; i < jsonList.length; i++) {
             var destination = jsonList[i];

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