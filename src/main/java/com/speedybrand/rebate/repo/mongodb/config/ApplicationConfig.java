package com.speedybrand.rebate.repo.mongodb.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.speedybrand.rebate.repo.mongodb.codecs.ClaimStatusCodec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Autowired
    private MongoDbConfiguration configuration;

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
                .applyConnectionString(new ConnectionString(configuration.getConnectionString()))
                .codecRegistry(codecRegistry)
                .build();
        return MongoClients.create(settings);
    }

}
