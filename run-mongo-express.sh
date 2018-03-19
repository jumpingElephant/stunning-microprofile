sudo docker run \
    --name mongo-express \
    --network microprofile-network \
    --link microprofile-mongo:mongo \
    --publish 8081:8081 \
    --rm \
    --detach \
    mongo-express
