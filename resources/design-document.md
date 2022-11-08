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

U1. As a user, I want a curated list of popular travel destinations.

U2. As a user, I want to be presented with travel destinations based off of my preferences.

U3. As a user, I want to select a set of favorite locations and retrieve the results.

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

* String countryId;

* String locationId;

* Set&lt;Category&gt; categories;

### CategoryModel (Interface?)
* String categoryId;
* String category;

### FavoritesModel
* Set&lt;Destination&gt; destinations;

* String userId (UUID);

## 6.2 Get Destination
This endpoint will by default return a random list of destinations. Accepts GET request to /destinations. If unable to access database throws DatabaseInaccessibleException, notifies user that the database is unavailable.

## 6.3 Get Category
Accepts a GET request to /categories with destinationId. This endpoint will return a list of categories a given destination fits into. If destinationId does not exist, throws InvalidDestinationIdException.

## 6.4 Create Favorites 
Accepts a POST request to /favorites. Then auto-generates UUID as the partition key. Saves favorites to a Dynamodb table. Then returns a secret URL for the user to keep and return their favorite destinations. 

## 6.5 Get Favorites
Accepts a GET request to /favorites with the provided URL. Returns the list of favorite destinations. Throws MalformedURLException if URL is not found. 

# 7. Tables

## 7.1  Destinations table
destinationId // partition key, string

countryId // string 

locationId // string 

category // stringSet

![image](https://user-images.githubusercontent.com/66507929/200614280-8850f6c8-3e92-44e5-afc7-c80478275595.png)

## 7.2 Categories table
categoryId // partition key, string (Beaches, Mountains, Best for Tourism, Best for Foodies, Best Museums, Best Night Life, Most Walkable)

![image](https://user-images.githubusercontent.com/66507929/200614095-b5ebf3ef-0cd5-412d-b187-c84ffc70bb24.png)

## 7.3 Favorites table
userId // partition key, string

destinationId // string

# 8. Pages
<img width="928" alt="Screen Shot 2022-11-08 at 9 49 45 AM (1)" src="https://user-images.githubusercontent.com/66507929/200613551-790bccd2-451f-4a69-ac3a-0754595eb736.png">
<img width="923" alt="Screen Shot 2022-11-08 at 9 50 58 AM" src="https://user-images.githubusercontent.com/66507929/200613862-92edfcdf-9d1f-46e2-9c13-add7da98e5a0.png">
<img width="923" alt="Screen Shot 2022-11-08 at 9 50 58 AM" src="https://i.postimg.cc/vmxrdQDm/favorites-List.png">


