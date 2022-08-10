package core.threading.transactions.impl;

import core.threading.transactions.CustomLogger;
import core.threading.transactions.TransactionManagement;
import core.threading.transactions.entities.Account;
import core.threading.transactions.entities.Transaction;

/**
 * @author Aleksey Morozov
 * @since 01.08.2022
 */
public class TransactionManagementImpl implements TransactionManagement {

    private final Transaction transaction;
    private final Account acc1;
    private final Account acc2;

    private final int transactionCash;
    private final CustomLogger logger;

    public TransactionManagementImpl(Account acc1,
                                     Account acc2,
                                     int transactionCash,
                                     CustomLogger logger) {
        this.transaction = new Transaction(acc1, acc2);
        this.acc1 = acc1;
        this.acc2 = acc2;
        this.transactionCash = transactionCash;
        this.logger = logger;
    }

    @Override
    public void run() {
        if (!acc1.isBusy() && !acc2.isBusy() && acc1.isValid(transactionCash)) {
            transaction.doTransaction(transactionCash);
            logger.logging("Transaction complete");
        }
        if (acc1.isBusy()) {
            logger.logging(String.format("Account #1 have status isBusy = %b", acc1.isBusy()));
        }
        if (acc2.isBusy()) {
            logger.logging(String.format("Account #2 have status isBusy = %b", acc2.isBusy()));
        }

    }
}
