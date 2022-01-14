package com.cardanonft.api.vo.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserRole {
    /**
     * 스프링 시큐리티 5 에서는 권한의 관련된 체크를 ROLE_ 시작하는지로 확인을 한다.
     * 따라서 다른 권한을 만들때도 ROLE_로 시작하는 권한을 만드는걸 권장한다.
     */
    GUEST("GUEST", "guest"),
    USER("USER", "user"), // 일반사용자
    ADMIN("ADMIN", "admin"); // 관리자


    private final String key;
    private final String title;
}
