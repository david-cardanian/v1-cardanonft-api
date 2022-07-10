package com.cardanonft.api.service;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.entity.UserEntity;
import com.cardanonft.api.entity.UserTokenHistory;
import com.cardanonft.api.entity.WebgameBuildInfo;
import com.cardanonft.api.entity.WebgameScoreboard;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.response.auth.UserGameProfileResponse;
import com.cardanonft.api.response.game.GameContextResponse;
import com.cardanonft.api.response.game.GameScoreResponse;
import com.cardanonft.api.vo.game.GameScore;
import org.joda.time.DateTimeZone;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {
    private static Logger logger = LoggerFactory.getLogger(GameService.class);

    private final WebgameBuildInfoRepository webgameBuildInfoRepository;
    private final WebgameScoreboardRepository webgameScoreboardRepository;
    private final UserRepository userRepository;
    private final UserTokenHistoryRepository userTokenHistoryRepository;
    private final AuthService authService;

    public GameService(WebgameBuildInfoRepository webgameBuildInfoRepository ,
                       UserRepository userRepository,
                       UserTokenHistoryRepository userTokenHistoryRepository,
                       WebgameScoreboardRepository webgameScoreboardRepository,
                       AuthService authService) {
        this.webgameBuildInfoRepository = webgameBuildInfoRepository;
        this.webgameScoreboardRepository = webgameScoreboardRepository;
        this.userTokenHistoryRepository = userTokenHistoryRepository;
        this.userRepository = userRepository;
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
                .screenWidth(webgameBuildInfo.getScreenWidth())
                .screenHeight(webgameBuildInfo.getScreenHeight())
                .build();
    }

    // get unity context list
    public List<GameContextResponse> getUnityContextList() {
        List<WebgameBuildInfo> webgameBuildInfoList = webgameBuildInfoRepository.findByEnabled(true);
        return webgameBuildInfoList.stream().map((webgameBuildInfo -> {
            return GameContextResponse.builder()
                    .gameId(webgameBuildInfo.getId())
                    .codeUrl(webgameBuildInfo.getCodeUrl())
                    .dataUrl(webgameBuildInfo.getDataUrl())
                    .loaderUrl(webgameBuildInfo.getLoaderUrl())
                    .frameworkUrl(webgameBuildInfo.getFrameworkUrl())
                    .screenWidth(webgameBuildInfo.getScreenWidth())
                    .screenHeight(webgameBuildInfo.getScreenHeight())
                    .build();
        })).collect(Collectors.toList());

    }

    public void setGameScore(String token, int gameId, int score, String gameHash) throws Exception {
        UserEntity userEntity = authService.findUser(token);
        WebgameScoreboard webgameScoreboard = new WebgameScoreboard();

        webgameScoreboard.setScore(score);
        webgameScoreboard.setUserId(userEntity.getUserId());
        webgameScoreboard.setGameId(gameId);
        webgameScoreboard.setGameHash(gameHash);
        webgameScoreboard.setNickName(userEntity.getNickName());
        webgameScoreboard.setCreatedAt(new Date());
        webgameScoreboard.setUpdatedAt(new Date());

        webgameScoreboardRepository.save(webgameScoreboard);
    }

    public GameScoreResponse getGameScoreList(int gameId) throws Exception {
        // 7개 뽑음.
        List<WebgameScoreboard> webgameScoreboardList =
                webgameScoreboardRepository.findTop7ByGameIdAndEnabledOrderByScoreDescCreatedAtDesc(gameId, true);
        WebgameBuildInfo webgameBuildInfo = webgameBuildInfoRepository.findFirstByIdAndEnabled(gameId, true);

        return GameScoreResponse.builder()
                .gameName(webgameBuildInfo.getGameName())
                .gameScoreList(webgameScoreboardList.stream().map(webgameScoreboard -> {
                    return GameScore.builder()
                            .score(webgameScoreboard.getScore())
                            .gameId(webgameScoreboard.getGameId())
                            .nickName(webgameScoreboard.getNickName())
                            .build();
                }).collect(Collectors.toList()))
                .build();

    }

    public UserGameProfileResponse getUserGameProfile(String token) throws Exception {
        UserEntity userEntity = authService.findUser(token);

        return UserGameProfileResponse.builder()
                .userId(userEntity.getUserId())
                .nickname(userEntity.getNickName())
                .tokenBalance(userEntity.getTokenBalance())
                .build();
    }

    /**
     * 10로그씩 차감.
     * @param token
     * @throws Exception
     */
    @Transactional
    public boolean insertLogToken(String token, String roomName) throws Exception {
        UserEntity userEntity = authService.findUser(token);
        if(userEntity.getTokenBalance().compareTo(BigDecimal.TEN) < 0) {
            // Todo: 잔액이 10보다 낮으면 또 리턴이 다름.
            return false;
        }

        // todo: 10로그 차감하고 기록 남기고. 방에 참가.
        BigDecimal userTokenBalance = userEntity.getTokenBalance();
        // 10 로그 차감.
        BigDecimal afterInsertToken = userTokenBalance.add(BigDecimal.valueOf(10));

        // user 테이블 갱신.
        userEntity.setTokenBalance(afterInsertToken);
//        userRepository.save(userEntity);

        // user_token_history 테이블에 내역 삽입
        // todo: 나머지 정보들은 어디에서 어떻게?
        UserTokenHistory userTokenHistory = new UserTokenHistory();
        userTokenHistory.setUserId(userEntity.getUserId());
        userTokenHistory.setBalance(afterInsertToken.longValue());

//        userTokenHistoryRepository.save(userTokenHistory);

        return true;
    }


}
