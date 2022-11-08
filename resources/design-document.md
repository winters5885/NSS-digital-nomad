# Design Document


## _Digital Nomad_ Design
## 0. Introduction
The Digital Nomad website is a resource for travellers to discover new and exciting destinations to visit and explore.

## 1. Problem Statement

Provide customers with a user experience that makes choosing where to travel easy. Our application will recommend destinations based on user input, gathering data from a variety of categories that the user will select. Users can save their travel options and view them at a later time.

## 2. Top Questions to Resolve in Review

Q. Where is our location data coming from? 

A. Data is gathered through web scraping.

Q. Will users be required to create a "profile"? Or will it be an optional feature that will unlock the ability to save results?

A. Creating a profile will be optional.

Q. How will we "stash" non-profile users data?

A. Stored in a temporary table.
## 3. Use Cases

U1. As a user, I want a curated list of popular travel destinations.

U2. As a user, I want to be presented with travel destinations based off of my preferences.

U3. As a user, I want to have a profile that stores my previous results.

U4. As a user, I want the ability to name my results.

## 4. Project Scope

### 4.1. In Scope

1. Creating and retrieving a travel itinerary/travel options.
2. Retrieving all favorites a user has created.

### 4.2. Out of Scope

1. Sharing of information between users.

# 5. Proposed Architecture Overview
Destination Objects. Category Objects. User Objects. Favorite Objects. We will use an API gateway and Lambda to create these endpoints (GetDestination, GetCategory, CreateUser and GetFavorites) We will store travel location reccommendations in a DynamoDB table. Favorite destinations will be stored in DynamoDB. We will provide a web interface for users to generate travel recommendations and/or view their favorite destinations. 

# 6. API

## 6.1. Public Models

### DestinationModel
* String destinationId;

* String countryId;

* String cityId;

* Set<Category> categories;

### CategoryModel (Interface?)
* String categoryId;
* String category;

### FavoritesModel
* List&lt;Destination&gt;;

* String userId;

* String name;
  
### UserModel 
* String userId;

## 6.2 GetDestination
This endpoint will by default return a list of most popular destinations. Accepts GET request to /destinations. If unable to access database throws DatabaseInaccessibleException, notifies user that the database is unavailable.


## 6.3 GetCategory
Accepts a GET request to /categories with destinationId. This endpoint will return a list of categories a given destination fits into. If destinationId does not exist, throws InvalidDestinationIdException.

## 6.4 GetFavorites 
Accepts a GET request to user/favorites. It will prompt for a username and if one is provided, it will return the users favorite destinations. If the user has no favorites, a NoFavoritesFoundException will be thrown and it will prompt them to create one.

## 6.5 CreateUser
Accepts a POST request to /user. Accepts username and password. Returns the users information confirming creation of profile. Throws UserNameNotAvailableException. Throws InvalidUserNameException.

# 7. Tables

## 7.1  Destinations table
destinationId // partition key, string

countryId // string 

cityId // string 

category // stringSet

![image](https://user-images.githubusercontent.com/66507929/200614280-8850f6c8-3e92-44e5-afc7-c80478275595.png)

## 7.2 Categories table
categoryId // partition key, string (Beaches, Mountains, Best for Tourism, Best for Foodies, Best Museums, Best Night Life, Most Walkable)

![image](https://user-images.githubusercontent.com/66507929/200614095-b5ebf3ef-0cd5-412d-b187-c84ffc70bb24.png)

## 7.3 Favorites table
userId // partition key, string

destinationId // sort key, string

![image](https://user-images.githubusercontent.com/66507929/200637784-a9a327ac-52f9-4f8d-a40a-4c431d2c1ae2.png)

# 8. Pages
<img width="928" alt="Screen Shot 2022-11-08 at 9 49 45 AM (1)" src="https://user-images.githubusercontent.com/66507929/200613551-790bccd2-451f-4a69-ac3a-0754595eb736.png">
<img width="923" alt="Screen Shot 2022-11-08 at 9 50 58 AM" src="https://user-images.githubusercontent.com/66507929/200613862-92edfcdf-9d1f-46e2-9c13-add7da98e5a0.png">
<img width="923" alt="Screen Shot 2022-11-08 at 9 50 58 AM" src="https://i.postimg.cc/vmxrdQDm/favorites-List.png">


