package com.ticco.common.exception;

public class UnAuthorizedException extends TiccoException {

    public UnAuthorizedException(String message) {
        super(message, ErrorCode.UNAUTHORIZED_EXCEPTION);
    }
}
