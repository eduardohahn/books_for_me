# books_for_me


## Step 1

Create a blank MySQL database and setup the connection information in application.properties.

The tables will be created automatically.

## Step 2

Import the CSV file located at "src/main/resources" through the API "/import/books" using the method POST.

## Step 3

Create an user through the API "/api/v1/user" using POST method and the following structure:


Content-Type: application/json


{
	"username" : "eduardo",
	"email" : "eduardo.hahn@outlook.com",
	"country" : "br"
}


## Step 4

Create feedbacks through the API "/api/v1/feedback" using POST method and the following structure:


Content-Type: application/json


{
	"username" : "eduardo",
	"book" : "122947576",
	"feedback" : "LIKED"
}


Feedback types: LIKED("Liked the book"), DISLIKED("Disliked the book"), NOTINTERESTED("Not interested");

## Step 5

Request for feedbacks through the API "api/v1/recommendations/{username}" using GET method

