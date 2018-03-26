docker run \
    --rm \
    --publish 8080:8080 \
    --link microprofile-mongo:mongo \
    --link microprofile-consul:consul \
    --network microprofile-network \
    --detach \
    wildfly-hello-world-service
