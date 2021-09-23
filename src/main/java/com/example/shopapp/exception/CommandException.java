package com.example.shopapp.exception;

public class CommandException extends Exception {

    /**
     * Instantiates a new CommandException.
     */
    public CommandException() {
        super();
    }

    /**
     * Instantiates a new CommandException.
     *
     * @param message the message
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Instantiates a new CommandException.
     *
     * @param message   the message
     * @param throwable the throwable
     */
    public CommandException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * Instantiates a new CommandException.
     *
     * @param throwable the throwable
     */
    public CommandException(Throwable throwable) {
        super(throwable);
    }
}
