package Lesson8.Client;

public class ClientConnectionException extends RuntimeException {
    public ClientConnectionException(String message) {
        super(message);
    }

    public ClientConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}

