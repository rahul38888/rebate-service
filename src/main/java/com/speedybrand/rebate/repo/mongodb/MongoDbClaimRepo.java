package com.speedybrand.rebate.repo.mongodb;

import com.mongodb.client.MongoClient;
import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.repo.IClaimRepo;
import com.speedybrand.rebate.repo.mongodb.common.MongoDbRepository;
import com.speedybrand.rebate.repo.mongodb.condition.MongoDbEnabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Conditional(MongoDbEnabled.class)
public class MongoDbClaimRepo extends MongoDbRepository<Claim> implements IClaimRepo {

    @Autowired
    public MongoDbClaimRepo(final MongoClient client) {
        super(client, "rebate-service", "claims", Claim.class);
    }

    @Override
    public Claim create(final Claim claim) {
        collection.insertOne(claim);
        return claim;
    }

    @Override
    public List<Claim> getClaimsInRange(final LocalDateTime from, final LocalDateTime to) {
        return List.of();
    }
}
