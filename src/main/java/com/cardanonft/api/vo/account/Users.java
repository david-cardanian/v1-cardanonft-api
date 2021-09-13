package com.cardanonft.api.vo.account;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class Users {

    private String username;
    private String password;
    private int failCount;
    private Timestamp failExpiredAt;
    private byte isLocked;
    private byte isEnabled;
}
