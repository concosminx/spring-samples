Spring - Rest - Gretty - JDBC 
===========================

@see [original](https://github.com/PacktPublishing/Spring-5-in-7-Days-v-) source code

Run the app using
-----------------
- `gradle appStart` (to start the server)
- `gradle appStop` (to stop the server)

Test app using
--------------

- `http://localhost:8080/spring_rest_gretty_api/`
  * POST: `/create`
  * PUT: `/update`
  * PATCH: `/patch/{id}`
  * GET: `/{id}`
  * DELETE `/delete/{id}`


Example payload for create
```json
{
    "userId": 4,
    "userName" : "Test",
    "phone" : "123455"
}
```