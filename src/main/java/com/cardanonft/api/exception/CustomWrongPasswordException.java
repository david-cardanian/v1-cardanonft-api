package com.cardanonft.api.exception;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.interfaces.CustomException;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class CustomWrongPasswordException extends RuntimeException implements CustomException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private int returnCode;
    private String returnMessage;
    private int failCount;
    private byte isLocked;
    private Timestamp failExpiredAt;

    public CustomWrongPasswordException(RETURN_CODE returnCode) {
        super(returnCode.getReturnMessage());
        this.returnCode = returnCode.getReturnCode();
        this.returnMessage = returnCode.getReturnMessage();
    }

    public CustomWrongPasswordException(RETURN_CODE returnCode, String returnMessage) {
        super(returnCode.getReturnMessage());
        this.returnCode = returnCode.getReturnCode();
        this.returnMessage = returnMessage;
    }

    public CustomWrongPasswordException(RETURN_CODE returnCode, int failCount, byte isLocked, Timestamp failExpiredAt) {
        super(returnCode.getReturnMessage());
        this.returnCode = returnCode.getReturnCode();
        this.returnMessage = returnCode.getReturnMessage();
        this.failCount = failCount;
        this.isLocked = isLocked;
        this.failExpiredAt = failExpiredAt;
    }
}
