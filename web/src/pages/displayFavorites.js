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
    }
}

const main = async () => {
    const favorites = new DisplayFavorites();
    favorites.mount();
};

window.addEventListener('DOMContentLoaded', main);
