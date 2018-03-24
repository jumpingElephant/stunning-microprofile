sudo docker run \
    --name microprofile-mongo \
    --mount source=microprofile-mongo-volume,target=/data/db \
    --network microprofile-network \
    --publish 27017:27017 \
    --detach \
    --rm \
    mongo

