Secured REST Api for Stocks In SpringBoot V 1.0

Description

This is a Rest API that provides information about stock of a product and let you update the price of a stock if allowed. 

It uses spring boot as the framework, docker to contain the whole, swagger to document the contract for the api. As database it uses H2 in memory database.

It has unit tests in Junit, Mockito.

It is secured with oauth 2 and spring security.

How to Build the API

-	Using Docker:

 The API is configured to run in as docker image and it can be accessed by default at http://localhost:8080

To create the docker image use:

mvn package docker:build

-	Traditional approach:

Compile : mvn package install
Run: mvn spring-boot:run

Endpoints and API Documentation

Endpoints:

GET /api/stocks (get a list of stocks) 
GET /api/stocks/1 (get one stock from the list) 
PUT /api/stocks/1 (update the price of a single stock)
POST /api/stocks (create a stock)

for the full documentation of the api please authenticate and use your token on:
http://localhost:8080/swagger-ui.html?access_token=your-oauth-token

Authentication Process to use the API

This api uses web authentication with user and password. Bearer token and refresh token are provided.

To authenticate against oauth2 in this api please use for example the following request:

general user:

-	permissions: will be able to only do get requests so only retrieve the list of stocks and a specific stock
-	retrieve general user token: curl -X POST --user 'web:secret' -d 'grant_type=password&username=generaluser&password=Stock@p1General' http://localhost:8080/oauth/token

Store or admin user:
-	permissions: will be able to get, put, post requests to retrieve create and update the list of stocks and a specific stock

-	Retrieve admin token: curl -X POST --user 'web:secret' -d 'grant_type=password&username=userstore&password=Stock@p1GeneralUserStore' http://localhost:8080/oauth/token


How to use the API:

-	First authenticate against /oauth/token
-	Then replace the token in “your_token” 


the API can be used as for example:

-	To get a stock:  GET localhost:8080/api/stocks/3?access_token=your_token

-	to create a stock :

curl -X POST \
'http://localhost:8080/api/stocks?access_token=your access token' \
-H 'cache-control: no-cache' \
-H 'content-type: application/json' \
-d '{
"id":2,
"name":"new new stock",
"currentPrice":28,
"quantity":100,
"description":"my description"
}'
