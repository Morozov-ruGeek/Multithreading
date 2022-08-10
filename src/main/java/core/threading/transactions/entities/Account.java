package core.threading.transactions.entities;

import core.threading.transactions.Validator;

/**
 * @author Aleksey Morozov
 * @since 01.08.2022
 */
public class Account extends BaseEntity implements Validator {
    private int cash;
    private boolean isBusy;

    public Account() {
    }

    public Account(int id, int cash) {
        super(id);
        this.cash = cash;
        this.isBusy = false;
    }

    public int getCash() {
        return cash;

    }

    public boolean isBusy() {
        return isBusy;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Account: id - ").append(super.getId()).append(", ")
                .append("cash: ").append(cash);
        return stringBuffer.toString();
    }

    @Override
    public boolean isValid(int transactionCash) {
        return this.getCash() - transactionCash >= 0;
    }
}
