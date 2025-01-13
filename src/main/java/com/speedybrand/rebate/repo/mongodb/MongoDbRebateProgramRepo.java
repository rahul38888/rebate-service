package com.speedybrand.rebate.repo.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.mongodb.condition.MongoDbEnabled;
import com.speedybrand.rebate.repo.mongodb.config.MongoDbConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import static com.speedybrand.rebate.utils.CommonUtil.ID;
import static com.speedybrand.rebate.utils.CommonUtil.enrichRebateProgram;

@Repository
@Conditional(MongoDbEnabled.class)
public class MongoDbRebateProgramRepo extends MongoDbRepository<RebateProgram> implements IRebateProgramRepo {

    @Autowired
    public MongoDbRebateProgramRepo(final MongoClient client, final MongoDbConfiguration configuration) {
        super(client, configuration.getDatabaseName(), configuration.getRebateProgramCollection(), RebateProgram.class);
    }

    @Override
    public RebateProgram get(final String rebateProgramId) {
        return collection.find(Filters.eq(ID, rebateProgramId), RebateProgram.class).first();
    }

    @Override
    public RebateProgram create(final RebateProgram rebateProgram) {
        enrichRebateProgram(rebateProgram);
        collection.insertOne(rebateProgram);
        return rebateProgram;
    }
}
