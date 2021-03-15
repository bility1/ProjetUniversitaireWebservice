#!/bin/sh
docker-compose down &&
docker-compose rm &&
#docker-compose pull &&
docker-compose build --no-cache &&
docker-compose up -d --force-recreate