# Design Document

## Instructions

_Replace italicized text (including this text!) with details of the design you are proposing for your team project. (Your replacement text shouldn't be in italics)._

_You should take a look at the [example design document](example-design-document.md) in the same folder as this template for more guidance on the types of information to capture, and the level of detail to aim for._

## _Project Title_ Design

## 1. Problem Statement

Provide customers with a user experience that makes choosing where to travel easy. Our application will recommend destinations based on user input, gathering data from a variety of categories that the user will select. Users can save their travel options and view them at a later time.

## 2. Top Questions to Resolve in Review

1. Where is our location data coming from?
2. Will users be required to create a "profile"? Or will it be an optional feature that will unlock the ability to save results?
3. How will we "stash" non-profile users data?

## 3. Use Cases

U1. As a user, I want a curated list of popular travel destinations.

U2. As a user, I want to be presented with travel destinations based off of my preferences.

U3. As a user, I want to have a profile that stores my previous results. (Extension)

U4. As a user, I want the ability to name my results. (Extension)

## 4. Project Scope

### 4.1. In Scope

1. Creating and retrieving a travel itinerary/travel options.
2. Retrieving all itineraries a user has created. (Extension)

### 4.2. Out of Scope

1. Sharing of information between users.

# 5. Proposed Architecture Overview
Destination Objects. Category Objects. User Objects (Extension). Itinerary Objects (Extension). We will use an API gateway and Lambda to create these endpoints (GetDestination, GetCategory, CreateUser and GetFavorites (Extension)) We will store travel location reccommendations in a DynamoDB table. Itineraries will be stored in DynamoDB. We will provide a web interface for users to generate travel recommendations and/or view their itineraries. 

# 6. API

## 6.1. Public Models

### DestinationModel
* String destinationId;

* String countryId;

* String cityId;

* Set<Category> categories;

### CategoryModel (Interface?)
* String category;
### ItineraryModel (Extension)
* List&lt;Destination&gt;;

* String userId;

* String name;
  
### UserModel (Extension)

* String userId;

* String password;

## 6.2 GetDestination
This endpoint will by default return a list of most popular destinations. Accepts GET request to /destinations. If unable to access database throws DatabaseInaccessibleException, notifies user that the database is unavailable.


## 6.3 GetCategory
Accepts a GET request to /categories with destinationId. This endpoint will return a list of categories a given destination fits into. If destinationId does not exist, throws InvalidDestinationIdException.

## 6.4 GetItinerary (Extension)
Accepts a GET request to user/itineraries. It will prompt for a username and if one is provided, it will return the users itineraries. If the user has no itineraries, a NoItinerariesFoundException will be thrown and it will prompt them to create one.

## 6.5 CreateUser (Extension)
Accepts a POST request to /user. Accepts username and password. Returns the users information confirming creation of profile. Throws UserNameNotAvailableException. Throws InvalidUserNameException.

# 7. Tables

## 7.1  Destinations table
destinationId // partition key, string

countryId // string 

cityId // string 

category // stringSet

## 7.2 Categories table
destinationId // partition key, string

categoryId // string (Beaches, Mountains, Best for Tourism, Best for Foodies, Best Museums, Best Night Life, Most Walkable)

## 7.3 Itinerary "Favorites" table (Extension)
userId // partition key, string

destinationId // sort key, string

# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the doggie detail page”)_
