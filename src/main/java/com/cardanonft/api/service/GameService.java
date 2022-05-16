package com.cardanonft.api.service;

import com.cardanonft.api.entity.UserEntity;
import com.cardanonft.api.entity.WebgameBuildInfo;
import com.cardanonft.api.entity.WebgameScoreboard;
import com.cardanonft.api.repository.WebgameBuildInfoRepository;
import com.cardanonft.api.repository.WebgameScoreboardRepository;
import com.cardanonft.api.response.game.GameContextResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GameService {
    private static Logger logger = LoggerFactory.getLogger(GameService.class);

    private final WebgameBuildInfoRepository webgameBuildInfoRepository;
    private final WebgameScoreboardRepository webgameScoreboardRepository;
    private final AuthService authService;

    public GameService(WebgameBuildInfoRepository webgameBuildInfoRepository ,
                       WebgameScoreboardRepository webgameScoreboardRepository,
                       AuthService authService) {
        this.webgameBuildInfoRepository = webgameBuildInfoRepository;
        this.webgameScoreboardRepository = webgameScoreboardRepository;
        this.authService = authService;
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

    public void setGameScore(String token, int gameId, int score, String gameHash) throws Exception {
        UserEntity userEntity = authService.findUser(token);
        WebgameScoreboard webgameScoreboard = new WebgameScoreboard();

        webgameScoreboard.setScore(score);
        webgameScoreboard.setUserId(userEntity.getUserId());
        webgameScoreboard.setGameId(gameId);
        webgameScoreboard.setGameHash(gameHash);

        webgameScoreboardRepository.save(webgameScoreboard);
    }
}
