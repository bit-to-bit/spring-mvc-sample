package org.java.dev.exception;
public class NoteServiceException extends Exception {
    public NoteServiceException(String message) {
        super(message);
    }
    public NoteServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
