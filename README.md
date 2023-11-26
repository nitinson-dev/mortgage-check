
# Mortgage Check Application

Simple implementation to check Mortgage Feasibility as per the assignment provided.


## API Reference

Basic authentication Enabled.

Header Params:

| Parameter | Value     |
| :-------- | :------- |
| `Authorization` | `Basic YWRtaW46cGFzc3dvcmQ=` |

Authorization Value is fixed at the moment. This can be optimized further if needed.

#### Get all Mortgage Interest Rates

```http
  GET /api/interest-rates
```
###### Sample Request:

curl -k -v --header 'Authorization: Basic YWRtaW46cGFzc3dvcmQ=' 'http://%host%:%port%/api/interest-rates'

#### Post to check mortgage eligibility

```http
  POST /api/mortgage-check
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `maturityPeriod`      | `int` | **Required**. number of years |
| `income`      | `BigDecimal` | **Required**. in EUR |
| `loanValue`      | `BigDecimal` | **Required**. in EUR |
| `homeValue`      | `BigDecimal` | **Required**. in EUR |

###### Sample Request:

curl -k -v 'http://%host%:%port%/api/mortgage-check' --header 'Content-Type: application/json' --data '{
"maturityPeriod": "10",
"income": "80000",
"loanValue": "320000",
"homeValue": "500000"
}'

## Deployment

Gitlab CI file is configured to build and push the docker image to docker hub - https://hub.docker.com/repository/docker/nitinson/mortgage-check/general



From here on you can pull the image on your server and deploy the code or use Kubernetes to further configure the container orchestration for the automation.

##### Using Docker command

```bash
  docker run -d -p 5000:8081 nitinson/mortgage-check:1.1
```

##### Using Kubernetes

We can define Kubernetes deployment.yml and add image and repository details

##### Using Maven

1. Build the project using
   `mvn clean install`
2. Run using `mvn spring-boot:run`
3. The web application is accessible via localhost:8081
4. Use username as 'admin' and password as 'password' to login to demo


## Optimizations

Added basic authentication with fixed username and password.
This can be changed later to use Bearer Authentication with username and password stored in database.


## Roadmap

- Enable SSL for the service



## Authors

- [@soninitin](https://gitlab.com/soninitin)

