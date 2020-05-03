# Work sample
This is a small Spring Boot backend that connects to an API and saves responses to a MongoDB.

## How to run
```
$ wget https://github.com/jahwag/worksample/blob/master/docker-compose.yml
$ docker-compose up -d
```

## Options
##### Running MongoDB separately
`$ docker run --name mongo -p 27017:27017 -d mongo:latest`

##### Running Backend separately
`$ docker run --name worksample -d jahwag/worksample:latest`

You can specify a custom MongoDB hostname and port using environment variables:

`$ docker run --name worksample -d jahwag/worksample:latest -e "spring.data.mongodb.host=<host>" -e "spring.data.mongodb.port=<port>`"

## Viewing
##### View backend logs
`$ docker logs worksample`

##### View database content using mongo express
`$ docker run --name express -d mongo-express:latest`

## Development
##### Building source code
This project can be built using Maven and JDK >= 1.8:

`$ mvn clean package`

##### Building Docker image
`$ docker build .`

## Quick links
* [DockerHub Repository](https://hub.docker.com/repository/docker/jahwag/worksample)
* [GitHub Repository](https://github.com/jahwag/worksample)