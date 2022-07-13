package com.cardanonft.api.request.game;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalculateTokenRequest {
    private String roomId;
    private String team; // 승리팀.
}
