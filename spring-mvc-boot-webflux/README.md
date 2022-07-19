Spring Boot - Webflux - Mongo
=============================

@see [original](https://github.com/PacktPublishing/Spring-5-in-7-Days-v-) source code

Run the app using
-----------------
- `gradle bootRun` 

Test the app using a REST client
--------------------------------

- `http://localhost:8080`

  * POST: `/create` 
  * GET: `/`
  * GET: `/{userId}`
  * PUT: `/update`
  * DELETE: `/delete/{userId}`
  

Payload example for create or update

```json
{
  "userId": 1,
  "userName" : "Batman",
  "phone" : "+40 123 123"
}
```
