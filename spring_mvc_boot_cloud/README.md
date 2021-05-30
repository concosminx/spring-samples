Spring - Boot
=============

Based [on](https://github.com/PacktPublishing/Spring-5.0-Project-Building-a-Travel-Website-v-) 

Uses:
- gradle, spring boot, junit, jpa
- websocket, thymeleaf, hystrix, spring security, webjars

Run with `gradle bootRun`

Test the app using: 

- POST: `http://localhost:8080/reactive/travel`
- GET: `http://localhost:8080/reactive/travel/{userId}`

Payload example for POST:
```json
{
    "userId" : "1",
    "source" : "Paris",
    "destination" : "BARCELONA"
}
```

- mvc: `http://localhost:8080/mvc/createTravel` and `http://localhost:8080/mvc/all-travels`
- security: `http://localhost:8080/secret`
- spring rest data repo: `http://localhost:8080/travel`
- systrix: `http://localhost:8080/travel/destination-details/CUCUBAU`
