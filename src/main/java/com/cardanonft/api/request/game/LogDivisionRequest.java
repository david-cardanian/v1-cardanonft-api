package com.cardanonft.api.request.game;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class LogDivisionRequest {
    private String photonRoomName;      // 포톤 룸 이름 ( id )
    private List<String> getPlayers;    // 승리한 플레이어들.
}
