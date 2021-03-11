## Registration Service

Registration Service Docker built command

```shell script
docker build -t registration:1.0 .
```

Registration Service Docker run command

```shell script
docker run -d -p 8081:8081 --rm registration:1.0
```

***

##### Registration service endpoints

> The Documentation
>
> [localhost:8081/swagger-ui.html](http://localhost:8081/swagger-ui.html)
>
---
> [localhost:8081/slow](http://localhost:8081/slow)
>
---
>
> [localhost:8081/registration/seller-list](http://localhost:8081/registration/seller-list)
>
> [localhost:8081/registration/add-seller](http://localhost:8081/registration/add-seller)
>
***

> want to test we can use apache bench

- [x] docker registration service
