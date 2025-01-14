package com.speedybrand.rebate.repo.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.mongodb.config.MongoDbConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import static com.speedybrand.rebate.utils.CommonUtil.enrichRebateProgram;

@Repository
@ConditionalOnProperty(name = "database", havingValue = "mongodb")
public class MongoDbRebateProgramRepo extends MongoDbRepository<RebateProgram> implements IRebateProgramRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbRebateProgramRepo.class);

    @Autowired
    public MongoDbRebateProgramRepo(final MongoClient client, final MongoDbConfiguration configuration) {
        super(client, configuration.getDatabaseName(), configuration.getRebateProgramCollection(), RebateProgram.class);
    }

    @Override
    @Cacheable("rebatePrograms")
    public RebateProgram get(final String rebateProgramId) {
        LOGGER.info("Fetching rebate program from database, rebateProgramId = {}, caching = true", rebateProgramId);
        return collection.find(Filters.eq(RebateProgram.DbConstant.ID, rebateProgramId), RebateProgram.class).first();
    }

    @Override
    public RebateProgram create(final RebateProgram rebateProgram) {
        enrichRebateProgram(rebateProgram);
        collection.insertOne(rebateProgram);
        return rebateProgram;
    }
}
