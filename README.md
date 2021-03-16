# Music Server with Group Listening

A static music file server with listening rooms for multiple clients to join and hear simultaneous streams of music. The app uses a Spring Boot backend and a React frontend.

This is my senior project at NMU fo the CS480 course.

## Getting Started

_Running Frontend - React_

* Navigate into the src/main/js folder and run the command `npm install`
* `npm start` will run the UI frontend, available at [localhost:3000](http://localhost:3000).

_Running Backend - Spring Boot_

* In the root project directory, run the command `gradle bootRun`
* The server will be listening on port 8080. 
* When the frontend is running, all requests to port 3000 will be proxied to port 8080. Test that everything is running by navigating to [localhost:3000/api/gms/hello](http://localhost:8080/api/gms/hello).

_Adding music_

Put .mp3 music files into `src/main/resources/music`. The music can be in folders up to 2 directories deep.

_Swagger REST Documentation_

Once Spring Boot is running, the interactive API documentation can be viewed at:

[localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)