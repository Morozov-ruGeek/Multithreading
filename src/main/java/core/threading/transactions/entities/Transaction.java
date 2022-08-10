package core.threading.transactions.entities;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Aleksey Morozov
 * @since 01.08.2022
 */
public class Transaction {

    private final Account acc1;
    private final Account acc2;

    public Transaction(Account acc1, Account acc2) {
        this.acc1 = acc1;
        this.acc2 = acc2;
    }

    public void doTransaction(int transactionCash) {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            acc1.setBusy(true);
            acc2.setBusy(true);
            acc1.setCash(acc1.getCash() - transactionCash);
            acc2.setCash(acc2.getCash() + transactionCash);
            acc1.setBusy(false);
            acc2.setBusy(false);
        } finally {
            lock.unlock();
        }
    }


}
