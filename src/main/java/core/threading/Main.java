package core.threading;

import core.threading.transactions.CustomLogger;
import core.threading.transactions.TransactionManagement;
import core.threading.transactions.entities.Account;
import core.threading.transactions.entities.BaseEntity;
import core.threading.transactions.impl.CustomLoggerImpl;
import core.threading.transactions.impl.TransactionManagementImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Aleksey Morozov
 * @since 01.08.2022
 */
public class Main {
    private static final int CASH_FOR_ACCOUNT = 100;
    private static final Integer COUNT_ACCOUNTS = 10;
    private static final int COUNT_THREADS = 15;
    private static final int MIN_COUNT_ACCOUNTS = 1;

    private static final ExecutorService threads = Executors.newFixedThreadPool(COUNT_THREADS);

    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>(COUNT_ACCOUNTS);
        fillAccountList(accounts);

        CustomLogger logger = new CustomLoggerImpl();
        logger.logging(String.format("Total cash before transaction cycle %d", totalCash(accounts)));
        accounts.forEach(System.out::println);
        int countOfCycle = 0;
        while (countOfCycle <= 100) {
            int transactionCash = ThreadLocalRandom.current().nextInt(10, CASH_FOR_ACCOUNT);
            Account acc1 = getRandomAccountForTransaction(accounts);
            Account acc2 = getRandomAccountForTransaction(accounts);
            if (acc1.getId() != acc2.getId()) {
                TransactionManagement tm = new TransactionManagementImpl(acc1, acc2, transactionCash, logger);
                threads.execute(tm);
                countOfCycle++;
            }
        }
        try {
            stop();
        } catch (InterruptedException e) {
            logger.logging(e.getMessage());
        }
        accounts.forEach(System.out::println);
        logger.logging(String.format("Total cash after transaction cycle %d", totalCash(accounts)));
    }

    private static void fillAccountList(List<Account> accounts) {
        int i = 1;
        while (i <= COUNT_ACCOUNTS) {
            accounts.add(new Account(i, CASH_FOR_ACCOUNT));
            i++;
        }
    }

    private static int totalCash(List<Account> accounts) {
        return accounts.stream()
                .mapToInt(Account::getCash)
                .sum();
    }

    private static Account getRandomAccountForTransaction(List<Account> entities) {
        return entities.get(ThreadLocalRandom.current().nextInt(MIN_COUNT_ACCOUNTS, COUNT_ACCOUNTS));
    }

    private static void stop() throws InterruptedException {
        if (threads.isTerminated()) {
            threads.shutdown();
        } else{
            threads.awaitTermination(5, TimeUnit.SECONDS);
            threads.shutdown();
        }
    }
}
