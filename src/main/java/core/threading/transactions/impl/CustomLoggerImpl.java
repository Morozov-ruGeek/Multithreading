package core.threading.transactions.impl;

import core.threading.transactions.CustomLogger;

/**
 * @author Aleksey Morozov
 * @since 05.08.2022
 */
public class CustomLoggerImpl implements CustomLogger {
    @Override
    public void logging(String message) {
        System.out.println(message);
    }
}
