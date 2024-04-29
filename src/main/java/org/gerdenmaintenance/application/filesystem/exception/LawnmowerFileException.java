package org.gerdenmaintenance.application.filesystem.exception;

public class LawnmowerFileException extends RuntimeException {
    public LawnmowerFileException(String msg) {
        super(msg);
    }

    public LawnmowerFileException(String msg, Exception ex) {
        super(msg, ex);
    }
}
