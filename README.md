
# Spring (boot) data redis.

Exemplo utilizando spring boot e redis

build: `mvn install dockerfile:build`
push : `mvn install dockerfile:push`


run on docker:

    docker run -d -p 6379:6379 --name redis -i -t redis:3.2.5-alpine

    docker run -d -p 8080:8080 --link redis:redis -t luizvilelamarques/redis-sample

   

