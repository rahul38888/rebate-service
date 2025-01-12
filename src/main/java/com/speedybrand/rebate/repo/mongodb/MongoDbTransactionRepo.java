package com.speedybrand.rebate.repo.mongodb;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PrimaryKey;
import com.mongodb.client.MongoClient;
import com.mongodb.client.model.Filters;
import com.speedybrand.rebate.pojo.RebateProgram;
import com.speedybrand.rebate.pojo.Transaction;
import com.speedybrand.rebate.repo.IRebateProgramRepo;
import com.speedybrand.rebate.repo.ITransactionRepo;
import com.speedybrand.rebate.repo.mongodb.common.MongoDbRepository;
import com.speedybrand.rebate.repo.mongodb.condition.MongoDbEnabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Repository;

import static com.speedybrand.rebate.utils.CommonUtil.enrichTransaction;

@Repository
@Conditional(MongoDbEnabled.class)
public class MongoDbTransactionRepo extends MongoDbRepository<Transaction> implements ITransactionRepo {

    @Autowired
    public MongoDbTransactionRepo(final MongoClient client) {
        super(client, "rebate-service", "transactions", Transaction.class);
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
