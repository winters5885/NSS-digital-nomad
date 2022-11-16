import DigitalNomadClient from "../api/digitalNomadClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SearchCategories extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['populateCityDropDown','populateCategoryDropDown', 'mount', 'submit', 'redirectToViewPlaylist'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewPlaylist);
        this.header = new Header(this.dataStore);
    }

    mount() {
        //document.getElementById('TAKE ME THERE').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new DigitalNomadClient();
        //this.populateCityDropDown();
        this.populateCategoryDropDown();
    }

    async submit() {
        document.getElementById('TAKE_ME_THERE').innerText = 'Loading...';
        const results = await this.client.getCategory(category);
        //const user = this.dataStore.get('username');
        const playlist = await this.client.createPlaylist(playlistName, user, tags);
        //this.dataStore.set('playlist', playlist);
        document.getElementById('TAKE_ME_THERE').innerText = 'Get';
    }

    redirectToViewPlaylist() {
        const playlist = this.dataStore.get('playlist');
        if (playlist != null) {
            window.location.href = `/playlist.html?id=${playlist.id}`;
        }
    }

    
    populateCityDropDown() {
        var cityList = ["Miami", 
                        "Phoenix", 
                        "Whitefish"
                    ];
        var html = [];

        for (var i = 0; i < cityList.length; i++) {
               html.push("<option>" + cityList[i] + "</option>");
        }
        document.getElementById("cityList").innerHTML = html.join("");
    }

    populateCategoryDropDown() {
        var categoryList = ["Beach", 
                        "Mountains", 
                        "Nightlife"
                    ];
        var html = [];

        for (var i = 0; i < categoryList.length; i++) {
               html.push("<option>" + categoryList[i] + "</option>");
        }
        document.getElementById("categoryList").innerHTML = html.join("");
    }

}
const main = async () => {
    const getCategory = new SearchCategories();
    getCategory.mount();
};

window.addEventListener('DOMContentLoaded', main);