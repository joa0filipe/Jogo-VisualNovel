package Exceptions;

import java.io.IOException;

public class CatalogoConquistasException extends IOException {
    public CatalogoConquistasException(String message) {
        super(message);
    }

    public CatalogoConquistasException(String message, Throwable cause) {
        super(message, cause);
    }
}
