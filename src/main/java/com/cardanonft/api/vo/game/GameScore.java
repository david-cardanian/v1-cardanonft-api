package com.cardanonft.api.vo.game;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class GameScore {
    private String nickName;
    private Integer gameId;
    private Integer score;
}
