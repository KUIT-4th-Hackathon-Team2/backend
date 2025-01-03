package com.konkukrent.demo.exception;

import com.konkukrent.demo.exception.exceptionClass.CustomException;
import com.konkukrent.demo.exception.properties.ErrorCode;

public class SessionException extends CustomException {

    public SessionException(ErrorCode errorCode) {
        super(errorCode);
    }

    public SessionException(ErrorCode errorCode, String runtimeValue) {
        super(errorCode, runtimeValue);
    }
}
