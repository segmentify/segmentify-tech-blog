# Order Management

***

Order Management Docker built command

```shell script
docker build -t ordermanagement:1.0 .
```

Order Management Service Docker run command

```shell script
docker run -d -p 8080:8080 --rm ordermanagement:1.0
```

***

##### Order Management service endpoints

> The Documentation
>
> [localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
>
---
> [localhost:8080/bulkhead](http://localhost:8080/bulkhead)
>
---
>
> [localhost:8080/register/seller-list](http://localhost:8080//register/seller-list)
>
> [localhost:8080/register/seller-circuit-breaker](http://localhost:8080//register/seller-circuit-breaker)
>
> [localhost:8080/register/seller-retry](http://localhost:8080//register/seller-retry)
>
> [localhost:8080/register/seller-rate-limiter](http://localhost:8080//register/seller-rate-limiter)
>
***

- [x] docker order management
- [ ] docker compose

