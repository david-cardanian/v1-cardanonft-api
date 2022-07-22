package com.cardanonft.api.response.game;
import java.util.List;

import com.cardanonft.api.vo.game.GameHistory;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameScoreHistoryResponse {
    private List<GameHistory> gameHistories;
    private int currentPage;
    private long totalCount;
    private int totalPages;
}


