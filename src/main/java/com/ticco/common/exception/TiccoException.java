package com.ticco.common.exception;

import lombok.Getter;

@Getter
public abstract class TiccoException extends RuntimeException {

    private final ErrorCode errorCode;

    public TiccoException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus() {
        return errorCode.getStatus();
    }
}
