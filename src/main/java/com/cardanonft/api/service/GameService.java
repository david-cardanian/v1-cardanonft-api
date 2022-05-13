package com.cardanonft.api.service;

import com.cardanonft.api.entity.WebgameBuildInfo;
import com.cardanonft.api.repository.WebgameBuildInfoRepository;
import com.cardanonft.api.response.game.GameContextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private static Logger logger = LoggerFactory.getLogger(GameService.class);

    private final WebgameBuildInfoRepository webgameBuildInfoRepository;

    public GameService(WebgameBuildInfoRepository webgameBuildInfoRepository) {
        this.webgameBuildInfoRepository = webgameBuildInfoRepository;
    }

    // get unity context randomly or specific
    public GameContextResponse getUnityContext(String gameName) {
        WebgameBuildInfo webgameBuildInfo =
                gameName != null ?
                        webgameBuildInfoRepository.findByGameNameAndEnabled(gameName, true) :
                        webgameBuildInfoRepository.findRandomOneGame();
        return GameContextResponse.builder()
                .codeUrl(webgameBuildInfo.getCodeUrl())
                .dataUrl(webgameBuildInfo.getDataUrl())
                .loaderUrl(webgameBuildInfo.getLoaderUrl())
                .frameworkUrl(webgameBuildInfo.getFrameworkUrl())
                .build();
    }
}
