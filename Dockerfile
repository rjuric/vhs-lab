FROM maven:3.9.8-eclipse-temurin-22-alpine AS build
WORKDIR /app
COPY . .
RUN apk update && apk add openssl && \
    mkdir ./src/main/resources/certs/ && \
    openssl genrsa -out ./src/main/resources/certs/keypair.pem 2048 && \
    openssl rsa -in ./src/main/resources/certs/keypair.pem -pubout -out ./src/main/resources/certs/public.pem  && \
    openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in ./src/main/resources/certs/keypair.pem -out ./src/main/resources/certs/private.pem  && \
    mvn clean package -DskipTests

FROM eclipse-temurin:22-alpine
WORKDIR /app
COPY --from=build /app/target/vhs-lab-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "vhs-lab-0.0.1-SNAPSHOT.jar"]
