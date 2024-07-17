#!/usr/bin/env sh

. .env.development

mkdir -p ./src/main/resources/certs/
openssl genrsa -out ./src/main/resources/certs/keypair.pem 2048
openssl rsa -in ./src/main/resources/certs/keypair.pem -pubout -out ./src/main/resources/certs/public.pem
openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in ./src/main/resources/certs/keypair.pem -out ./src/main/resources/certs/private.pem
rm ./src/main/resources/certs/keypair.pem

docker run -d --rm --name vhs_lab_development \
  -e POSTGRES_DB=$POSTGRES_DB \
  -e POSTGRES_USER=$POSTGRES_USER \
  -e POSTGRES_PASSWORD=$POSTGRES_PASSWORD \
  -e POSTGRES_HOST_AUTH_METHOD=password \
  -p $DB_PORT:5432 postgres:16.3

./mvnw spring-boot:run -Dspring-boot.run.profiles=development