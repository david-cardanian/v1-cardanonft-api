package com.cardanonft.api.response.game;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class GameScoreResponse {
    private String nickName;
    private Integer gameId;
    private Integer score;
}
