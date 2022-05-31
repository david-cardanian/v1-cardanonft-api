package com.cardanonft.api.response.game;

import com.cardanonft.api.vo.game.GameScore;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Data
@Getter
@Setter
@Builder
public class GameScoreResponse {
    private String gameName;
    private List<GameScore> gameScoreList;
}
