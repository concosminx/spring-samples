Spring - Rest - Reactive - Gretty
=================================

@see [original](https://github.com/PacktPublishing/Spring-5-in-7-Days-v-) source code

Run the app using
-----------------
- `gradle appStart` (to start the server)
- `gradle appStop` (to stop the server)

Test the app using rest client
------------------------------

- `http://localhost:8080/spring_rest_gretty_reactive` with endpoints
  * GET: `/`
  * POST: `/create` or '/'
  * PUT: `/update`
  * ...

Payload example for create

```json
{
    "userId": 1,
    "userName" : "Test",
    "phone" : "123455"
}
```

