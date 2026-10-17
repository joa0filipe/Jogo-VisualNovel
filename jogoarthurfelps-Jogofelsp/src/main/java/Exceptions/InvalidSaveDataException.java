package Exceptions;

import java.io.IOException;

public class InvalidSaveDataException extends IOException {
    public InvalidSaveDataException(String message) {
        super(message);
    }

    public InvalidSaveDataException(String message, Throwable cause) {
        super(message, cause);
    }
}
