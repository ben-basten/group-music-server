# Music Server with Group Listening

A static music file server with listening rooms for multiple clients to join and hear simultaneous streams of music. The app uses a Spring Boot backend and a React frontend.

This is my senior project at NMU fo the CS480 course.

---

## Getting Started

_Frontend - React_

* Navigate into the src/js folder and run the command `npm install`
* `npm start` will run the UI frontend, available at [localhost:3000](http://localhost:3000).
* If data such as the music listings aren't appearing in the UI and the console has the error `Cross-Origin Request Blocked: The Same Origin Policy disallows reading the remote resource at http://localhost:8080/api/gms/tracks. (Reason: CORS header ‘Access-Control-Allow-Origin’ missing).`:
    * For your browser of choice, get a CORS changer Add-on/Extension. One that I've found works well is "Moesif CORS".
    * Enable Moesif CORS to allow cross-domain requests.
    * Refresh the page. You're all set!

_Backend - Spring Boot_

* In the root project directory, run the command `gradle bootRun`
* The server will be listening on port 8080. Test that it is working by navigating to [localhost:8080/api/gms/hello](http://localhost:8080/api/gms/hello).