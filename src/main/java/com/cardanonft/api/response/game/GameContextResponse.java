package com.cardanonft.api.response.game;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Builder
public class GameContextResponse {
    private String loaderUrl;
    private String dataUrl;
    private String frameworkUrl;
    private String codeUrl;
}
