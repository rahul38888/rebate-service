package com.speedybrand.rebate.repo.mongodb;

import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.repo.mongodb.condition.MongoDbEnabled;
import com.speedybrand.rebate.repo.mongodb.config.MongoDbConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import static com.speedybrand.rebate.utils.CommonUtil.enrichTransaction;

@Repository
@Conditional(MongoDbEnabled.class)
public class MongoDbTransactionRepo extends MongoDbRepository<Transaction> implements ITransactionRepo {

    @Autowired
    public MongoDbTransactionRepo(final MongoClient client, final MongoDbConfiguration configuration) {
        super(client, configuration.getDatabaseName(), configuration.getTransactionCollection(), Transaction.class);
    }

    @Override
    public Transaction get(final String transactionId) {
        return collection.find(Filters.eq("_id", transactionId), Transaction.class).first();
    }

    @Override
    public Transaction record(final Transaction transaction) {
        enrichTransaction(transaction);
        collection.insertOne(transaction);
        return transaction;
    }

}
