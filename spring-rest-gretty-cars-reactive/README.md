**NOT WORKING ON TOMCAT**

Spring - REST - Gretty - Reactive
=================================

@see [original](https://github.com/PacktPublishing/Spring-5-in-7-Days-v-) 
source code

Run the app using
-----------------
- `gradle appStart` (to start the server)
- `gradle appStop` (to stop the server)


Test the app using REST client
------------------------------

- `http://localhost:8080/spring_rest_gretty_cars_reactive/`

  * GET: `/user/searchById/{id}`
  * POST: `/user/registration`
  * GET: `/user/all`
  * PUT: `/user/update` 
  * DELETE: `/user/delete/{id}`
  * POST: `/user/carselection`
  * POST: `/car/registration`
  * GET: `/car/searchById/{id}`
  * GET: `/car/all`
  * PUT: `/car/update`
  * DELETE: `/car/delete/{id}`

Payloads: 

```json
{
  "userId": 1,
  "userName" : "Batman",
  "phone" : "+40 123 123",
  "address" : "Bucharest, Unirii, nr. 205",
  "age" : 42,
  "wallet" : 590
}
```

