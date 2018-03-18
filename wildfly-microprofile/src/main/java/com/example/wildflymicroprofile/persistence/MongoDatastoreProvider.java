package com.example.wildflymicroprofile.persistence;

import com.mongodb.MongoClient;
import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

@ApplicationScoped
public class MongoDatastoreProvider {

    public static final String DEFAULT_MAP_PACKAGE = "com.example.wildflymicroprofile.persistence";

    @Inject
    @Named("mongodbprofile")
    private MongoClient mongoClient;

    @Produces
    public Datastore produceDatastore() {

        Morphia morphia = new Morphia();
        morphia.mapPackage(DEFAULT_MAP_PACKAGE);

        return morphia.createDatastore(mongoClient, "microData");
    }

}
