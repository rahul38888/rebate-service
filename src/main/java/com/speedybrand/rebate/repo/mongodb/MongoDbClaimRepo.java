package com.speedybrand.rebate.repo.mongodb;

import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.pojo.Claim;
import com.speedybrand.rebate.pojo.ClaimStatus;
import com.speedybrand.rebate.pojo.Statistics;
import com.speedybrand.rebate.repo.IClaimRepo;
import com.speedybrand.rebate.repo.mongodb.common.MongoDbRepository;
import com.speedybrand.rebate.repo.mongodb.condition.MongoDbEnabled;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;

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
    public List<Statistics> getClaimAggregationReport(final LocalDateTime from, final LocalDateTime to) {
        final Bson match = Aggregates.match(Filters.and(Filters.gte("claimDate", from),
                Filters.lte("claimDate", to)));
        final Bson group = Aggregates.group("$status",
                Accumulators.sum("total", "$claimAmount"),
                Accumulators.sum("count", 1));

        final List<Bson> aggregateQuery = Arrays.asList(match, group);
        final AggregateIterable<Document> response = collection.aggregate(aggregateQuery, Document.class);

        final List<Statistics> result = new ArrayList<>();
        response.forEach(doc -> {
            final Statistics status = Statistics.builder()
                    .status(ClaimStatus.from(doc.getInteger("_id")))
                    .total(doc.getDouble("total"))
                    .count(doc.getInteger("count"))
                    .build();
            result.add(status);
        });

        return result;
    }
}
