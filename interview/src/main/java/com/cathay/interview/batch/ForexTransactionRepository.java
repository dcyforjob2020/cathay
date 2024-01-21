package com.cathay.interview.batch;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cathay.interview.bean.ForexTransaction;

public interface ForexTransactionRepository extends MongoRepository<ForexTransaction, String> {
}