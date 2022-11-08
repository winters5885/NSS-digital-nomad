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

![image](https://user-images.githubusercontent.com/66507929/200614280-8850f6c8-3e92-44e5-afc7-c80478275595.png)

## 7.2 Categories table
destinationId // partition key, string

categoryId // string (Beaches, Mountains, Best for Tourism, Best for Foodies, Best Museums, Best Night Life, Most Walkable)

![image](https://user-images.githubusercontent.com/66507929/200614095-b5ebf3ef-0cd5-412d-b187-c84ffc70bb24.png)

## 7.3 Itinerary "Favorites" table (Extension)
userId // partition key, string

destinationId // sort key, string

# 8. Pages
<img width="928" alt="Screen Shot 2022-11-08 at 9 49 45 AM (1)" src="https://user-images.githubusercontent.com/66507929/200613551-790bccd2-451f-4a69-ac3a-0754595eb736.png">
<img width="923" alt="Screen Shot 2022-11-08 at 9 50 58 AM" src="https://user-images.githubusercontent.com/66507929/200613862-92edfcdf-9d1f-46e2-9c13-add7da98e5a0.png">

  


