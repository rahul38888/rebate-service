package com.speedybrand.rebate.repo.mongodb.common;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

public class MongoDbRepository<T> {

    protected final String databaseName;
    protected final String collectionName;
    protected final Class<T> classType;
    protected final MongoClient client;

    protected MongoCollection<T> collection;

    public MongoDbRepository(final MongoClient client, final String databaseName, final String collectionName,
                             final Class<T> classType) {
        this.client = client;
        this.databaseName = databaseName;
        this.collectionName = collectionName;
        this.classType = classType;
    }

    @PostConstruct
    public void init() {
        collection = client.getDatabase(this.databaseName).getCollection(collectionName, classType);
    }

    @PreDestroy
    public void destroy() {
        client.close();
    }

}
