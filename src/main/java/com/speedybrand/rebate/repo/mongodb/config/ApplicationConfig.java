package com.speedybrand.rebate.repo.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.speedybrand.rebate.repo.mongodb.common.codecs.ClaimStatusCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    private static final String connectionString = "mongodb://localhost:27017/";

    @Bean
    public MongoClient mongoClient() {
        final CodecRegistry pojoCodecRegistry =
                CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build());
        final CodecRegistry codecRegistry =
                CodecRegistries.fromRegistries(
                        CodecRegistries.fromCodecs(new ClaimStatusCodec()),
                        MongoClientSettings.getDefaultCodecRegistry(),
                        pojoCodecRegistry);
        final MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .codecRegistry(codecRegistry)
                .build();
        return MongoClients.create(settings);
    }

}
