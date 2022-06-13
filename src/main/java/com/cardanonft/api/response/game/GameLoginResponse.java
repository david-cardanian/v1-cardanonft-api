package com.cardanonft.api.response.game;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameLoginResponse {
    private String token;
    private String user_id;
}
