# Design Document


## _Digital Nomad_ Design
## 0. Introduction

The Digital Nomad website is a resource for travellers to discover new and exciting destinations to visit and explore.

## 1. Problem Statement

Provide customers with a user experience that makes choosing where to travel easy. Our application will recommend destinations based on user input, gathering data from a variety of categories that the user will select. Users can favorite their results and our application will provide a link for later access.

## 2. Top Questions to Resolve in Review

Q. Where is our location data coming from?

A. Data is gathered through web scraping.

Q. How will we provide a way for users to save their favorite destinations?

A. By providing a secret URL.

## 3. Use Cases

U1. As a user, I want a curated list of popular travel destinations to help me decide where to travel.

U2. As a user, I want to be presented with travel destinations based off of my preferences in order to automate narrowing my search. 

U3. As a user, I want to select a set of favorite locations and retrieve the results. I want my results saved for later viewing. 

## 4. Project Scope

### 4.1. In Scope

1. Generating travel recommendations filtered by user selected category.
2. Allowing for the saving and retrieving of favorite destinations.

### 4.2. Out of Scope

1. Sharing of information between users.
2. User profile creation.

# 5. Proposed Architecture Overview

Destination Objects. Category Objects. Favorite Objects. We will use an API gateway and Lambda to create these endpoints (GetDestination, GetCategory, CreateFavorites, and GetFavorites) We will store travel location reccommendations in a DynamoDB table. Favorite destinations will be stored in DynamoDB. We will provide a web interface for users to generate travel recommendations and/or view their favorite destinations.

# 6. API

## 6.1. Public Models

### DestinationModel
* String destinationId;

* String country;

* String cityName;

* Category category

### CategoryModel (Interface?)
* String category;

### FavoritesModel
* Set&lt;Destination&gt; destinations;

* String userId (UUID);


## 6.2 Get Destinations
This endpoint will by default return a random list of destinations. This endpoint will also returns a list of destinations based on category selection within the query.  Accepts GET request to /destinations. If unable to access database throws DatabaseInaccessibleException, notifies user that the database is unavailable.

## 6.3 Create Favorites
Accepts a POST request to /favorites. Then auto-generates UUID as the partition key. Saves favorites to a Dynamodb table. Then returns a secret URL for the user to keep and return their favorite destinations.

## 6.4 Get Favorites
Accepts a GET request to /favorites with UUID. Returns the list of favorite destinations. Throws UUIDNotFoundException if UUID is not found.

## 6.5 Get Single Destination
Accepts a GET request to /destinations. A single destination is returned. If unable to access database DatabaseInaccessibleException is thrown, notifies user tha the database is unavailable.

# 7. Tables

## 7.1  Destinations table
category // partition key, string

destinationID (UUID) // sort key, string

cityName // string

country // string



![image](https://user-images.githubusercontent.com/66507929/200938324-8b365fa3-2d00-4bdc-af40-2fdf53019278.png)

## 7.2 Categories table
category // partition key, string (Beaches, Mountains, Best for Tourism, Best for Foodies, Best Museums, Best Night Life, Most Walkable)

## 7.3 Favorites table
userId (UUID) // partition key, string

destinations //  string set

![image](https://user-images.githubusercontent.com/66507929/200665207-2c9b78d6-877c-4dd3-92dc-43a25cd051aa.png)
![image](https://user-images.githubusercontent.com/66507929/200665320-de825f98-a6bd-41a2-805a-ee6c92cdc86a.png)

## 7.4 All Categories for Destination Index (GSI)
destinationId (UUID) // partition key, string

citName // string

country // string

category // string

![image](https://user-images.githubusercontent.com/66507929/200937989-85dad107-75ac-4772-9785-ab08309007f8.png)

# 8. Pages
![Screenshot (2)](https://user-images.githubusercontent.com/66507929/200938993-e4ca09d2-21d7-4ed3-9f69-855c108bd0a1.png)
![Screenshot (3)](https://user-images.githubusercontent.com/66507929/200939075-4759b2e9-2939-447b-8ab2-b3eeb11f6358.png)
![Screenshot (4)](https://user-images.githubusercontent.com/66507929/200939130-b6cb2f75-4d16-4fd0-96eb-4fa4f3ad8c89.png)
