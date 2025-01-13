package com.speedybrand.rebate.repo.mongodb.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static com.speedybrand.rebate.repo.mongodb.config.MongoDbConfiguration.Constants.*;

@Getter
@Setter
@Component
public class MongoDbConfiguration {

    private final Environment environment;
    private final String connectionString;
    private final String databaseName;
    private final String rebateProgramCollection;
    private final String transactionCollection;
    private final String claimsCollection;

    public MongoDbConfiguration(@Autowired final Environment environment,
                                @Value(DATABASE_NAME) final String databaseName,
                                @Value(REBATE_PROGRAM_COLLECTION) final String rebateProgramCollection,
                                @Value(TRANSACTION_COLLECTION) final String transactionCollection,
                                @Value(CLAIMS_COLLECTION) final String claimsCollection) {
        this.environment = environment;
        this.connectionString = environment.getProperty(CONNECTION_STRING);
        this.databaseName = databaseName;
        this.rebateProgramCollection = rebateProgramCollection;
        this.transactionCollection = transactionCollection;
        this.claimsCollection = claimsCollection;
    }

    public static class Constants {
        public static final String CONNECTION_STRING = "CONNECTION_STRING";
        public static final String DATABASE_NAME = "${mongodb.database.name}";

        public static final String REBATE_PROGRAM_COLLECTION = "${mongodb.collection.rebateprogram}";
        public static final String TRANSACTION_COLLECTION = "${mongodb.collection.transactions}";
        public static final String CLAIMS_COLLECTION = "${mongodb.collection.claims}";
    }
}
