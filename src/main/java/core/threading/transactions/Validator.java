package core.threading.transactions;

/**
 * @author Aleksey Morozov
 * @since 05.08.2022
 */
public interface Validator {
    boolean isValid(int transactionCash);
}
