import DigitalNomadClient from "../api/digitalNomadClient";
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SearchCategories extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['populateCategoryDropDown', 'mount'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    mount() {
        this.header.addHeaderToPage();
        this.header.loadData();
        this.client = new DigitalNomadClient();
        this.populateCategoryDropDown();
    }

    async populateCategoryDropDown() {
    
        var html = [];

        var categoryList = await this.client.getCategoriesList();

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