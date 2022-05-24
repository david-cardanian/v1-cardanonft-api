package com.cardanonft.api.request.game;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@ApiModel
@Getter
@Setter
public class ScoreRequest {
    public int gameId;
    public String scoreData;
    public String dateTime;
    public int score;
}
