package io.openliberty.sample.inventory.client;

public class UnknownUrlException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public UnknownUrlException() {
        super();
    }

    public UnknownUrlException(String message) {
        super(message);
    }
}