#!/bin/sh

SECONDS=0

if [ "$1" = "native" ];
then
  docker build -f src/main/docker/Dockerfile.native -t rasithsaminda/rashith-events-native:1.0.0 .
else
  docker  build --no-cache  -f src/main/docker/Dockerfile.jvm -t rasithsaminda/rashith-events:1.0.0 .
fi

duration=$SECONDS
echo "$(($duration / 60)) minutes and $(($duration % 60)) seconds elapsed."
