package com.cardanonft.api.vo.game;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GameHistory {
    private boolean win;// win : true
    private String team; // blue, red
    private Long logToken;
    private String gameDate;
}
