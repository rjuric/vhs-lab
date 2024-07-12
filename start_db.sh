#!/usr/bin/env sh

touch .env

docker run --name vhs_lab \
  -e POSTGRES_DB=$DB_NAME \
  -e POSTGRES_USER=$DB_USERNAME \
  -e POSTGRES_PASSWORD=$DB_PASSWORD \
  -e POSTGRES_HOST_AUTH_METHOD=password \
  -p $DB_PORT:5432 postgres
