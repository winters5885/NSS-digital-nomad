import axios from "axios";
import BindingClass from "../util/bindingClass";

/**
 * Client to call the MusicPlaylistService.
 *
 * This could be a great place to explore Mixins. Currently the client is being loaded multiple times on each page,
 * which we could avoid using inheritance or Mixins.
 * https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Classes#Mix-ins
 * https://javascript.info/mixins
  */
export default class DigitalNomadClient extends BindingClass {

    constructor(props = {}){
        super();
        const methodsToBind = ['clientLoaded','getCategoriesList', 'getIdentity'];
        this.bindClassMethods(methodsToBind, this);
        this.props = props;
        axios.defaults.baseURL = INVOKE_URL;
        this.client = axios;
        this.clientLoaded(this.client);
    }

    /**
     * Run any functions that are supposed to be called once the client has loaded successfully.
     * @param client The client that has been successfully loaded.
     */
    clientLoaded(client) {
        if (this.props.hasOwnProperty("onReady")){
            this.props.onReady();
        }
    }

    /**
     * Get the identity of the current user
     * @param errorCallback (Optional) A function to execute if the call fails.
     * @returns The username and phonetool url for the current user.
     */
     async getIdentity(errorCallback) {
        try {
            const data = {'username': 'Nashville Software'};
            return data;
        } catch(error) {
            this.handleError(error, errorCallback)
        }
    }

    async getCategoriesList(errorCallback) {
        try {
            const response = await this.client.get(`categories`);
            return response.data.categoriesList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async getDestinationResultsList(category, errorCallback) {
        try {
            const response = await this.client.get(`/destinations/${category}`);
            return response.data.destinationList;
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

    async postFavorites(favoriteDestinations, errorCallback) {
        try {
            const response = await this.client.post('favorites', {
                favoriteDestinations: favoriteDestinations
            });
            return response.data.favoriteModel
        } catch (error) {
            this.handleError(error, errorCallback)
        }
    }

     /**
     * Helper method to log the error and run any error functions.
     * @param error The error received from the server.
     * @param errorCallback (Optional) A function to execute if the call fails.
     */
      handleError(error, errorCallback) {
        console.error(error);
        if (error.response.data.message !== undefined) {
            console.error(error.response.data.message)
        }
        if (errorCallback) {
            errorCallback(error);
        }
    }
}