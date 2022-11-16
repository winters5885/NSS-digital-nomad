import MusicPlaylistClient from '../api/musicPlaylistClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class CreatePlaylist extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['populateCityDropDown','populateCategoryDropDown', 'mount', 'submit', 'redirectToViewPlaylist'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewPlaylist);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load the MusicPlaylistClient.
     */

    mount() {
        //document.getElementById('create').addEventListener('click', this.submit);
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new MusicPlaylistClient();
        this.populateCityDropDown();
        this.populateCategoryDropDown();
    }

    /**
     * Method to run when the create playlist submit button is pressed. Call the MusicPlaylistService to create the
     * playlist.
     */
    async submit() {
        document.getElementById('create').innerText = 'Loading...';
        const playlistName = document.getElementById('playlist-name').value;
        const user = this.dataStore.get('username');
        const tagsText = document.getElementById('tags').value;
        let tags;
        if (tagsText.length < 1) {
            tags = null;
        } else {
            tags = tagsText.split(/\s*,\s*/);
        }

        const playlist = await this.client.createPlaylist(playlistName, user, tags);
        this.dataStore.set('playlist', playlist);
        document.getElementById('create').innerText = 'Create';
    }

    /**
     * When the playlist is updated in the datastore, redirect to the view playlist page.
     */
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

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const createPlaylist = new CreatePlaylist();
    createPlaylist.mount();
};

window.addEventListener('DOMContentLoaded', main);
