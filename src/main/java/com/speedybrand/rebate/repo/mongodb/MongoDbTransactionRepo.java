package com.speedybrand.rebate.repo.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.repo.mongodb.config.MongoDbConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import static com.speedybrand.rebate.utils.CommonUtil.enrichTransaction;

@Repository
@ConditionalOnProperty(name = "database", havingValue = "mongodb")
public class MongoDbTransactionRepo extends MongoDbRepository<Transaction> implements ITransactionRepo {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbTransactionRepo.class);

    @Autowired
    public MongoDbTransactionRepo(final MongoClient client, final MongoDbConfiguration configuration) {
        super(client, configuration.getDatabaseName(), configuration.getTransactionCollection(), Transaction.class);
    }

    @Override
    @Cacheable("transaction")
    public Transaction get(final String transactionId) {
        LOGGER.info("Fetching rebate program from database, transactionId = {}, caching = true", transactionId);
        return collection.find(Filters.eq(Transaction.DbConstant.ID, transactionId), Transaction.class).first();
    }

    @Override
    public Transaction record(final Transaction transaction) {
        enrichTransaction(transaction);
        collection.insertOne(transaction);
        return transaction;
    }

}
