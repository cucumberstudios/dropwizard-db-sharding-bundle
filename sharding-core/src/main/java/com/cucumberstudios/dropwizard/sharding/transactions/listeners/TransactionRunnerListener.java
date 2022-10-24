package com.cucumberstudios.dropwizard.sharding.transactions.listeners;

import com.cucumberstudios.dropwizard.sharding.transactions.TransactionContext;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * Created on 2019-01-17
 */
public interface TransactionRunnerListener {
    void onStart(UnitOfWork unitOfWork, TransactionContext transactionContext);

    void onFinish(boolean success, UnitOfWork unitOfWork, TransactionContext transactionContext, long timeElapsed);
}
