import DigitalNomadClient from "../api/digitalNomadClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SearchResults extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['displayCategory','populateResultsList','mount'], this);
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
        
        if (jsonList.length == 0) {
            document.getElementById("resultsList").innerHTML = "Return list is empty."
        }

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

            var checkbox = document.createElement('input');
            checkbox.className = 'container';
            checkbox.type = 'checkbox';
            checkbox.id = 'favoriteCheckbox' + i;
            checkbox.name = 'favorite';
            checkbox.value = destination.destinationID;

            var label = document.createElement('label')
            label.htmlFor = 'favorite';
            label.appendChild(document.createTextNode('Favorite'));

            var br = document.createElement('br');

            var container = document.getElementById('resultsList');
            container.appendChild(checkbox);
            container.appendChild(br);
        }

        var favoriteDestinations = [];

        var favoritesButton = document.getElementById('favoritesButton');
        favoritesButton.addEventListener('click', () => {

            for (var i = 0; i < jsonList.length; i++) {
                var checkbox = document.getElementById('favoriteCheckbox' + i);

                if(checkbox.checked) {
                    favoriteDestinations.push(checkbox.value);
                }
            }

            this.client.postFavorites(favoriteDestinations)
        });

        console.log("Favorites List: ", favoriteDestinations);

    }
}

const main = async () => {
    const results = new SearchResults();
    results.mount();
};

window.addEventListener('DOMContentLoaded', main);