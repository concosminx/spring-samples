## cURL to access Order Service ##

```
curl -X 'POST' \
  'http://localhost:9191/api/orders' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "name": "Mobile",
  "customerId": "JohnDoe",
  "productType": "Electronics",
  "quantity": 1,
  "price": 8600
}'
```