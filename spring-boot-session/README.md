# Getting Started

### Create a new REDIS container 
`$ docker run -p 16379:6379 -d redis:6.0 redis-server --requirepass "mypass"`

### Connect to REDIS container and use `redis-cli` to see the records
```bash
redis-cli -a mypass
KEYS *
```
or 
```bash
redis-cli
INFO keyspace
AUTH mypass
```

