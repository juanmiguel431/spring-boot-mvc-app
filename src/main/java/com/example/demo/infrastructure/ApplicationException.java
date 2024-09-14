package com.example.demo.infrastructure;

public class ApplicationException extends RuntimeException {

    private ErrorType errorType;

    public ErrorType getErrorType() {
        return errorType;
    }

    public void setErrorType(ErrorType errorType) {
        this.errorType = errorType;
    }

    public ApplicationException(ErrorType type, String message) {
        super(message);
        setErrorType(type);
    }

    public ApplicationException(ErrorType type, String message, Throwable cause) {
        super(message, cause);
        setErrorType(type);
    }

    public ApplicationException(ErrorType type, Throwable cause) {
        super(cause);
        setErrorType(type);
    }

    public ApplicationException(ErrorType type, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        setErrorType(type);
    }
}
