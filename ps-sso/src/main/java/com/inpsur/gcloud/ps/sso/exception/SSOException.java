package com.inpsur.gcloud.ps.sso.exception;


public class SSOException extends RuntimeException {
    public SSOException() {
    }

    public SSOException(String message) {
        super(message);
    }

    public SSOException(String message, Throwable cause) {
        super(message, cause);
    }

    public SSOException(Throwable cause) {
        super(cause);
    }

    public SSOException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
