# Spring Boot with Keycloak Demo 

## Keycloak setup (windows)

1. Download the server and start in development mode
   - keycloak-21.1.1
   - create a small bat file inside the server root (`%~dp0` is the folder in which the executing bat file resides)
  
```bat
set HOME_DIR=%~dp0
call %HOME_DIR%\bin\kc.bat start-dev
```

1. Goto to server [administration console](http://localhost:8080)
2. Create a new REALM with the desired name (eg. **SpringKeycloak**) 
3. Inside the new REALM, create a new client with type OpenID Connect (eg. **spring-keycloak-client**)
4. Add the valid redirect URIs (u can use `http://localhost:spring_boot_port/*`)
5. Create **user** and **admin** client roles
6. Create **app_admin** and **app_user** realm roles
7. Composite the realm roles with client roles using **Add associated roles**
8. Create a few users (with credentials and roles)
   - **global-admin** with the **app_admin** role
   - **user1** with the **app_user** role
   - **user2** with the **app_admin** and **app_user** roles
9. Go to Realm settings and click on the OpenID Endpoint (`http://localhost:8080/realms/SpringKeycloak/.well-known/openid-configuration`)
10. Make a post request to the **token** endpoint (with Postman or similar tool)
```bash
  curl --location --request POST 'http://localhost:8080/realms/SpringKeycloak/protocol/openid-connect/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'grant_type=password' \
--data-urlencode 'client_id=spring-keycloak-client' \
--data-urlencode 'username=user1' \
--data-urlencode 'password=a'
```
or

```bash
wget --no-check-certificate --quiet \
  --method POST \
  --timeout=0 \
  --header 'Content-Type: application/x-www-form-urlencoded' \
  --body-data 'grant_type=password&client_id=spring-keycloak-client&username=user1&password=a' \
   'http://localhost:8080/realms/SpringKeycloak/protocol/openid-connect/token'
```

11. Copy the access token and check the content in [https://jwt.io/](https://jwt.io/)
12. Change the access token expiration time under the token tab in Realm settings

## App testing  
- We need to send a valid access token obtained from the Keycloak
- `http://localhost:7070/api/test/anonymous` - no credentials needed
- `http://localhost:7070/api/test/admin` or `http://localhost:7070/api/test/user` - use the access token from (10) as Bearer according to the needed role
```bash
curl --location --request GET 'http://localhost:7070/api/test/admin' \
--header 'Authorization: Bearer eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJKdk1WaE9jSE5xTlFpNGxoc3FzVUVDVjlVMEk2RmxnN3R3bWZFUnprVThrIn0.....
```

Source [here](https://medium.com/geekculture/using-keycloak-with-spring-boot-3-0-376fa9f60e0b)