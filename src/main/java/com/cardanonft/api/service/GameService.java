package com.cardanonft.api.service;

import com.cardanonft.api.entity.*;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.response.auth.UserGameProfileResponse;
import com.cardanonft.api.response.game.GameContextResponse;
import com.cardanonft.api.response.game.GameScoreHistoryResponse;
import com.cardanonft.api.response.game.GameScoreResponse;
import com.cardanonft.api.vo.game.GameHistory;
import com.cardanonft.api.vo.game.GameScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
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

    public GameScoreHistoryResponse getGameScoreWithLogHistory(String token, int page) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yy.MM.dd");
        TimeZone utc = TimeZone.getTimeZone("UTC");
        formatter.setTimeZone(utc);

        Pageable pageable = PageRequest.of(page-1, 10, Sort.by(Sort.Direction.DESC,"historyId"));
        UserEntity userEntity = authService.findUser(token);
        Page<UserGameHistory> userGameHistoryList = userGameHistoryRepository.findByUserIdAndIsEnabled(userEntity.getUserId(), "0", pageable);

        GameScoreHistoryResponse gameScoreHistoryResponse = new GameScoreHistoryResponse();

        gameScoreHistoryResponse.setGameHistories(userGameHistoryList.getContent().stream().map(userGameHistory -> {
            GameHistory gameHistory = new GameHistory();
            gameHistory.setWin(Objects.equals(userGameHistory.getWinLose(), "1"));
            gameHistory.setLogToken(userGameHistory.getTokenEarned());
            gameHistory.setTeam(userGameHistory.getTeam());
            gameHistory.setGameDate(formatter.format(userGameHistory.getCreatedAt()));
            return gameHistory;
        }).collect(Collectors.toList()));
        gameScoreHistoryResponse.setCurrentPage(userGameHistoryList.getPageable().getPageNumber());
        gameScoreHistoryResponse.setTotalPages(userGameHistoryList.getTotalPages());
        gameScoreHistoryResponse.setTotalCount(userGameHistoryList.getTotalElements());
        gameScoreHistoryResponse.setPageSize(10);
        return gameScoreHistoryResponse;
    }

    public UserGameProfileResponse getUserGameProfile(String token) throws Exception {
        UserEntity userEntity = authService.findUser(token);
        BigDecimal devideDecimal = new BigDecimal(1000000);
        return UserGameProfileResponse.builder()
                .userId(userEntity.getUserId())
                .nickname(userEntity.getNickName())
                .tokenBalance(userEntity.getTokenBalance().divide(devideDecimal))
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
            userRepository.updateUserTokenBalance((userEntity.getTokenBalance().subtract(BigDecimal.TEN)), userEntity.getUserId());
            UserTokenHistory userTokenHistory = new UserTokenHistory();
            userTokenHistory.setUserId(userEntity.getUserId());
            userTokenHistory.setBalance(10000000L); // 10Log Token 10 * 1000000
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


    @Transactional
    public boolean calculateGameToken (String roomId, String winTeam) {
        try{
            BigDecimal totalToken = new BigDecimal(0);
            double winerCounrt = 0;
            List<String> winnerGameHistoryList = new ArrayList<>();
            Map<String, UserGameHistory> winnerGameHistoryMap = new HashMap<>();

            List<UserGameHistory> userGameHistoryList = userGameHistoryRepository.findAllByRoomNameAndIsEnabled(roomId, "1");
            for ( UserGameHistory userGameHistory : userGameHistoryList ) {
                if(userGameHistory.getTeam().equals(winTeam)) {
                    userGameHistory.setWinEarned("1");
                    userGameHistory.setWinLose("1");
                    userGameHistory.setIsEnabled("0");
                    winnerGameHistoryMap.put(userGameHistory.getUserId(), userGameHistory);
                    winnerGameHistoryList.add(userGameHistory.getUserId());
                    winerCounrt++;
                } else {
                    // 먼저 lose부터 업데이트. 10log 차감 기록.
                    userGameHistory.setWinEarned("0");
                    userGameHistory.setWinLose("0");
                    userGameHistory.setTokenEarned(-10000000L);
                    userGameHistory.setIsEnabled("0");
                    userGameHistoryRepository.save(userGameHistory);
                }
                totalToken = totalToken.add(BigDecimal.valueOf(10000000));

            }
            List<UserEntity> userEntityList = userRepository.findAllByUserIdInAndIsEnabled(winnerGameHistoryList, "1");
            // 전체 토큰을 승자 숫자대로 나눔.
            // 나눠서 수령
            BigDecimal winTokenPrice = totalToken.divide(BigDecimal.valueOf(winerCounrt));
            for(UserEntity userEntity : userEntityList) {
                userRepository.updateUserTokenBalance((userEntity.getTokenBalance().add(winTokenPrice)), userEntity.getUserId());

                UserGameHistory winnerGameHistory = winnerGameHistoryMap.get(userEntity.getUserId());
                winnerGameHistory.setTokenEarned(winTokenPrice.longValue());
                userGameHistoryRepository.save(winnerGameHistory);

                UserTokenHistory userTokenHistory = new UserTokenHistory();
                userTokenHistory.setUserId(userEntity.getUserId());
                userTokenHistory.setBalance(winTokenPrice.longValue());
                userTokenHistory.setType("W"); // 게임 win
                userTokenHistory.setIsEnabled("1");
                userTokenHistoryRepository.save(userTokenHistory);
            }
            return true;
        }catch (Exception e) {
            return false;
        }

    }


}
