package Exceptions;

import java.io.IOException;

public class SaveException extends IOException {
    public SaveException(String message, Throwable cause) {
        super(message, cause);
    }
}
