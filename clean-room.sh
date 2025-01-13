#!/bin/bash -e

# shellcheck disable=SC2046
docker stop $(docker ps -aq); docker rm -v $(docker ps -aq); docker rmi -f $(docker images -q); docker volume rm -f $(docker volume ls | grep -v DRIVER)
