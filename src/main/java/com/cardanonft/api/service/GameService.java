package com.cardanonft.api.service;

import com.cardanonft.api.entity.*;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.response.auth.UserGameProfileResponse;
import com.cardanonft.api.response.game.GameContextResponse;
import com.cardanonft.api.response.game.GameScoreResponse;
import com.cardanonft.api.vo.game.GameScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
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
    private final UserGameHistoryRepository userGameHistoryRepository;

    private final AuthService authService;

    public GameService(WebgameBuildInfoRepository webgameBuildInfoRepository ,
                       UserRepository userRepository,
                       UserTokenHistoryRepository userTokenHistoryRepository,
                       WebgameScoreboardRepository webgameScoreboardRepository,
                       UserGameHistoryRepository userGameHistoryRepository,
                       AuthService authService) {
        this.webgameBuildInfoRepository = webgameBuildInfoRepository;
        this.webgameScoreboardRepository = webgameScoreboardRepository;
        this.userTokenHistoryRepository = userTokenHistoryRepository;
        this.userRepository = userRepository;
        this.userGameHistoryRepository = userGameHistoryRepository;
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
     * @throws Exception
     */
    @Transactional
    public boolean insertLogToken(String roomName, List<String> blueTeam, List<String> redTeam) throws Exception {
        List<String> tempAllUserList = new ArrayList<>();
        tempAllUserList.addAll(blueTeam);
        tempAllUserList.addAll(redTeam);
        List<UserEntity> userEntityList = userRepository.findAllByUserIdInAndIsEnabled(tempAllUserList, "1");

        // user 검색 시 & 보상도 :: 동시성.
        // select for update
        for(UserEntity userEntity : userEntityList) {
            userRepository.updateUserTokenBalance((userEntity.getTokenBalance().min(BigDecimal.TEN)), userEntity.getUserId());
            UserTokenHistory userTokenHistory = new UserTokenHistory();
            userTokenHistory.setUserId(userEntity.getUserId());
            userTokenHistory.setBalance(10L);
            userTokenHistory.setType("J"); // 게임 Join.
            userTokenHistory.setIsEnabled("1");
            userTokenHistoryRepository.save(userTokenHistory);
        }

        List<UserGameHistory> userGameHistoryList = new ArrayList<>();
        for ( String blueTeamUserId : blueTeam) {
            UserGameHistory blueGameTokenHistory = new UserGameHistory();
            blueGameTokenHistory.setUserId(blueTeamUserId);
            blueGameTokenHistory.setTeam("blue");
            blueGameTokenHistory.setJoinPayed("1");
            blueGameTokenHistory.setIsEnabled("1");
            blueGameTokenHistory.setWinEarned("0");
            blueGameTokenHistory.setRoomName(roomName);
            userGameHistoryList.add(blueGameTokenHistory);
        }
        for (String redTeamUserId : redTeam ) {
            UserGameHistory redGameTokenHistory = new UserGameHistory();
            redGameTokenHistory.setUserId(redTeamUserId);
            redGameTokenHistory.setTeam("red");
            redGameTokenHistory.setJoinPayed("1");
            redGameTokenHistory.setIsEnabled("1");
            redGameTokenHistory.setWinEarned("0");
            redGameTokenHistory.setRoomName(roomName);
            userGameHistoryList.add(redGameTokenHistory);
        }

        userGameHistoryRepository.saveAll(userGameHistoryList);

        return true;
    }


}
