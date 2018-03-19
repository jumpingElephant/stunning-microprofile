package com.example.wildflymicroprofile.entity;

import com.example.wildflymicroprofile.control.MongoDatastoreProvider;
import com.example.wildflymicroprofile.persistence.Greeting;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MongoDatastoreProviderTest {

    @Test
    public void testMapPackage() {

        String expectedPackageName = Greeting.class.getPackageName();

        assertEquals(expectedPackageName, MongoDatastoreProvider.DEFAULT_MAP_PACKAGE);
    }

}