package com.konkukrent.demo.exception;

import com.konkukrent.demo.exception.exceptionClass.CustomException;
import com.konkukrent.demo.exception.properties.ErrorCode;

public class UserException extends CustomException {

    public UserException(ErrorCode errorCode) {
        super(errorCode);
    }

    public UserException(ErrorCode errorCode, String runtimeValue) {
        super(errorCode, runtimeValue);
    }
}
