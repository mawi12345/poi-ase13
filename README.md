App Engine POI Application
Copyright (C) 2013-2014 Wind Martin

## How to build and test

Requires [Apache Maven](http://maven.apache.org) 3.0 or greater, and JDK 7 in order to run.

To build, run

    mvn package

Building will run the tests, but to explicitly run tests you can use the test target.

    mvn test

To start the app on an local dev server, use the [App Engine Maven Plugin](http://code.google.com/p/appengine-maven-plugin/). Just run the command.

    mvn appengine:devserver

For further information, consult the [Java App Engine](https://developers.google.com/appengine/docs/java/overview) documentation.

## The REST API

In this API examples the domain `http://poi-ase13.appspot.com` is usesd. Exchange the domain to `http://http://localhost:8080` if you wont to test on your local app engine test server.
The API will allways return an `JSON` representation.

### List of POIs

To resive an list of POIs issue an `GET` request to `http://poi-ase13.appspot.com/resources/poi`.

	curl -X get http://poi-ase13.appspot.com/resources/poi
	
The server will return the first 30 results. To resive the next 30 results append the page parameter `p=1`.

	curl -X get http://poi-ase13.appspot.com/resources/poi?p=1
	
To query the list use the parameters `creator`, `category`, `name` and `p`.

	curl -X get http://poi-ase13.appspot.com/resources/poi?category=Demo%20Data
	
### Create an POI

To create an new POI send an `PUT` or `POST` request to `http://poi-ase13.appspot.com/resources/poi` with an valid `JSON` representation of the POI in the request body.

	curl -X put -H "Content-Type: application/json" -d '{"name":"Post Demo POI","latitude":51.6369641024136,"longitude":41.226032489550722,"creator":"curl","description":"this poi has been created by curl","category":"test"}' http://poi-ase13.appspot.com/resources/poi
	
or if the `JSON` body is stored in the file `poi.json`

	curl -X post -H "Content-Type: application/json" -d @poi.json http://poi-ase13.appspot.com/resources/poi 
	
The server will respond with an `JSON` POI representation including the `id` of the POI.

	{"name":"Post Demo POI","latitude":51.6369641024136,"longitude":41.226032489550725,"creator":"curl","description":"this poi has been created by curl","category":"test","id":5750085036015616}
	
If the POI is invalid the server will respond with an `4xx` status code indicating the error in the POI.

### Updating an POI

To update an existing POI issue an `POST` request to `http://poi-ase13.appspot.com/resources/poi/{ID}` with an valid `JSON` representation of the updated POI in the request body.

	curl -X post -H "Content-Type: application/json" -d @updated_poi.json http://poi-ase13.appspot.com/resources/poi/5750085036015616
	
### Delete an POI

To delete an POI send an `DELETE` request to `http://poi-ase13.appspot.com/resources/poi/{ID}`.

	curl -X delete http://poi-ase13.appspot.com/resources/poi/5750085036015616
	
to circumvent the javascript XMLHttpRequest limitations it is also possible to delete an POI with an `POST` request `http://poi-ase13.appspot.com/resources/poi/{ID}?methos=delete`.

	curl -X post -d "" http://poi-ase13.appspot.com/resources/poi/5750085036015616?methos=delete

