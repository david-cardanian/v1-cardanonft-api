package com.cardanonft.api.request.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class InsertTokenRequest {
    // 게임 룸 입장 시 플레이어 토큰과 room name으로 history를 설정함.
    private String roomName;
    private List<String> blueTeam;
    private List<String> redTeam;
}
