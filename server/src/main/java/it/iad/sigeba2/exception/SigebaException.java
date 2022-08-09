package it.iad.sigeba2.exception;

public class SigebaException extends Exception{

    public SigebaException() {
    }

    public SigebaException(String message) {
        super(message);
    }

    public SigebaException(String message, Throwable cause) {
        super(message, cause);
    }

    public SigebaException(Throwable cause) {
        super(cause);
    }

    public SigebaException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
