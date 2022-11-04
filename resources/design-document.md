# Design Document

## Instructions

_Replace italicized text (including this text!) with details of the design you are proposing for your team project. (Your replacement text shouldn't be in italics)._

_You should take a look at the [example design document](example-design-document.md) in the same folder as this template for more guidance on the types of information to capture, and the level of detail to aim for._

## _Project Title_ Design

## 1. Problem Statement

Provide customers with a user experience that makes choosing where to travel easy. Our application will reccommend destinations based on user input, gathering data from a variety of categories that the user will select. Users can save their travel options and view them at a later time.

## 2. Top Questions to Resolve in Review

_List the most important questions you have about your design, or things that you are still debating internally that you might like help working through._

1. Where is our location data coming from?
2. Will users be required to create a "profile"? Or will it be an optional feature that will unlock the ability to save results?
3. How will we "stash" non-profile users data?

## 3. Use Cases

_This is where we work backwards from the customer and define what our customers would like to do (and why). You may also include use cases for yourselves (as developers), or for the organization providing the product to customers._

U1. As a user, I want a curated list of popular travel destinations.

U2. As a user, I want to be presented with travel destinations based off of my preferences.

U3. As a user, I want to have a profile that stores my previous results.

U4. As a user, I want the ability to name my results.

## 4. Project Scope

_Clarify which parts of the problem you intend to solve. It helps reviewers know what questions to ask to make sure you are solving for what you say and stops discussions from getting sidetracked by aspects you do not intend to handle in your design._

### 4.1. In Scope

1. Creating and retrieving a travel itinerary/travel options.
2. Retrieving all itineraries a user has created.
_The functionality described above should be what your design is focused on. You do not need to include the design for any out of scope features or expansions._

### 4.2. Out of Scope

1. Sharing of information between users.

_The functionality here does not need to be accounted for in your design._

# 5. Proposed Architecture Overview

_Describe broadly how you are proposing to solve for the requirements you described in Section 2. This may include class diagram(s) showing what components you are planning to build. You should argue why this architecture (organization of components) is reasonable. That is, why it represents a good data flow and a good separation of concerns. Where applicable, argue why this architecture satisfies the stated requirements._
Destination Objects. User Objects. Itinerary Objects. We will use an API gateway and Lambda to create these endpoints (GetItinerary, CreateItinerary, CreateUser) We will store travel location reccommendations in a DynamoDB table. Itineraries will be stored in DynamoDB. We will provide a web interface for users to generate travel recommendations and/or view their itineraries. 

# 6. API

## 6.1. Public Models

//DestinationModel

//ItineraryModel

List<Destination>
String name;
  
//UserModel
  
String id;
String userName;
String password?;
List<Itinerary>;
  
 

## 6.2.GetItinerary.
Accepts a GET request to user/itinieraries. It will prompt for a username and if one is provided, it will return the users itineraries. If the user has no itineraries, a NoItinerariesFoundException will be thrown and it will prompt them to create one.  

  _Describe the behavior of the first endpoint you will build into your service API. This should include what data it requires, what data it returns, and how it will handle any known failure cases. You should also include a sequence diagram showing how a user interaction goes from user to website to service to database, and back. This first endpoint can serve as a template for subsequent endpoints. (If there is a significant difference on a subsequent endpoint, review that with your team before building it!)_

_(You should have a separate section for each of the endpoints you are expecting to build...)_

## 6.3 CreateUser.
  Accepts a POST request to /user. Accepts username and password. Returns the users information confirming creation of profile. Throws UserNameNotAvailableException. Throws InvalidUserNameException. 
  
## 6.4 GetDestination.
  This endpoint will by default return a list of most popular destinations. Accepts GET request to /destinations.

_(repeat, but you can use shorthand here, indicating what is different, likely primarily the data in/out and error conditions. If the sequence diagram is nearly identical, you can say in a few words how it is the same/different from the first endpoint)_

# 7. Tables
Destinations table. User table. Possibly an itinerary table.
_Define the DynamoDB tables you will need for the data your service will use. It may be helpful to first think of what objects your service will need, then translate that to a table structure, like with the *`Playlist` POJO* versus the `playlists` table in the Unit 3 project._

# 8. Pages

_Include mock-ups of the web pages you expect to build. These can be as sophisticated as mockups/wireframes using drawing software, or as simple as hand-drawn pictures that represent the key customer-facing components of the pages. It should be clear what the interactions will be on the page, especially where customers enter and submit data. You may want to accompany the mockups with some description of behaviors of the page (e.g. “When customer submits the submit-dog-photo button, the customer is sent to the doggie detail page”)_
