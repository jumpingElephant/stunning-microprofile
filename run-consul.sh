# Is the definition of the hostname required?
sudo docker run \
    --rm \
    --name microprofile-consul \
    --network microprofile-network \
    --detach \
    --publish 8400:8400 \
    --publish 8500:8500 \
    --publish 8600:53/udp \
    --hostname node1 \
    progrium/consul -server -bootstrap -ui-dir /ui