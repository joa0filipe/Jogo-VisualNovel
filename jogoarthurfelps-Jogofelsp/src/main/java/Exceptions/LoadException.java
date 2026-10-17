package Exceptions;

import java.io.IOException;

public class LoadException extends IOException {
    public LoadException(String message) {
        super(message);
    }

    public LoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
