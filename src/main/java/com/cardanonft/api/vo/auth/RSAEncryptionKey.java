package com.cardanonft.api.vo.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RSAEncryptionKey {

    private String token;
    private String privateKey;
    private String publicKey;
}
