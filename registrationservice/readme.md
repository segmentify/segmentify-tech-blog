
##Registration Service


Registration Service Docker built command
```shell script
docker build -t registrationservice:1.0 .
```

Registration Service Docker run command
```shell script
docker run -d -p 8081:8081 --rm registrationservice:1.0
```

***
##### Registration service endpoints
> [localhost:8081](http://localhost:8081)
>
> [localhost:8081/slow](http://localhost:8081/slow)
>
> [localhost:8081/error](http://localhost:8081/error)
>
> [localhost:8081/erratic](http://localhost:8081/erratic)
>
---
>
> [localhost:8081/registration](http://localhost:8081/registration)
>
> [localhost:8081/registration/add-seller](http://localhost:8081/registration/add-seller)
>
***

> want to test we can use apache bench


- [x] docker registration service