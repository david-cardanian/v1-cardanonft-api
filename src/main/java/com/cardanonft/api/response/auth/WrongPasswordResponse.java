package com.cardanonft.api.response.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.CustomWrongPasswordException;
import com.cardanonft.api.response.DefaultResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class WrongPasswordResponse extends DefaultResponse {

    @JsonProperty("fail_count")
    private int failCount;
    @JsonProperty("is_locked")
    private byte isLocked;
    @JsonProperty("fail_expired_at")
    private Timestamp failExpiredAt;

    public WrongPasswordResponse(RETURN_CODE returnCode) {
        super(returnCode);
    }

    public WrongPasswordResponse(RETURN_CODE returnCode, int failCount, byte isLocked, Timestamp failExpiredAt) {
        super(returnCode);
        this.failCount = failCount;
        this.isLocked = isLocked;
        this.failExpiredAt = failExpiredAt;
    }

    public WrongPasswordResponse(CustomWrongPasswordException e) {
        super(e.getReturnCode(), e.getReturnMessage());
        this.failCount = e.getFailCount();
        this.failExpiredAt = e.getFailExpiredAt();
        this.isLocked = e.getIsLocked();
    }
}
