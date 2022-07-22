package com.cardanonft.api.controller.game;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.entity.CardanoAddressEntity;
import com.cardanonft.api.entity.UserGameHistory;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.repository.CardanoAddressRepository;
import com.cardanonft.api.request.VillageListRequest;
import com.cardanonft.api.request.auth.AuthAdaRequest;
import com.cardanonft.api.request.auth.LoginVO;
import com.cardanonft.api.request.game.CalculateTokenRequest;
import com.cardanonft.api.request.game.InsertTokenRequest;
import com.cardanonft.api.request.game.ScoreRequest;
import com.cardanonft.api.request.game.TestRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.response.auth.AuthAdaResponse;
import com.cardanonft.api.response.auth.UserGameProfileResponse;
import com.cardanonft.api.response.game.GameContextResponse;
import com.cardanonft.api.response.game.GameLoginResponse;
import com.cardanonft.api.response.game.GameScoreHistoryResponse;
import com.cardanonft.api.response.game.GameScoreResponse;
import com.cardanonft.api.service.AuthService;
import com.cardanonft.api.service.GameService;
import com.cardanonft.api.util.DateUtil;
import com.cardanonft.api.vo.auth.AuthToken;
import com.cardanonft.api.vo.game.GameHistory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/game")
public class GameController {
    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final GameService gameService;
    private final AuthService authService;
    private final CollectionDao collectionDao;
    private final CardanoAddressRepository cardanoAddressRepository;

    public GameController(BCryptPasswordEncoder bCryptPasswordEncoder, GameService gameService, AuthService authService, CollectionDao collectionDao, CardanoAddressRepository cardanoAddressRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.gameService = gameService;
        this.authService = authService;
        this.collectionDao = collectionDao;
        this.cardanoAddressRepository = cardanoAddressRepository;
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getVillageList(

            @RequestHeader(value = "token", required = false) String token,
            @RequestBody TestRequest testRequest
    ) throws Exception {

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }

    /**
     * 게임 로그인은 팀을 정하는 것까지.
     *
     * @param loginVO
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse GameLogin(
            HttpServletRequest request,
            @RequestBody LoginVO loginVO
    ) throws Exception {
        logger.debug("login api start");
        try {
            logger.info(loginVO.getId());
            // 2. 토큰을 발급한다.
            String ip = getIpAddress(request);
            AuthToken token = authService.issueNAccessToken(
                    loginVO.getId().trim(),
                    null,
                    ip,
                    loginVO.getOs_type(),
                    null);
            authService.login(token, loginVO);

            // 좀 적은 정보를 전달.
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS,
                    GameLoginResponse.builder()
                            .token(token.getToken())
                            .user_id(token.getUser_id())
                            .build());
        } catch (CustomBadRequestException e) {
            throw e;
        } catch (Exception e) {
            logger.error("error", e);
            return new CardanoNftDefaultResponse(RETURN_CODE.ERROR, e);
        }
    }

    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    /**
     * 잔액 확인.
     *
     * @param token
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/nickBalance", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getNickTokenBalance(
            @RequestHeader(value = "token") String token
    ) throws Exception {
        try {
            UserGameProfileResponse userGameProfileResponse = gameService.getUserGameProfile(token);
            if (userGameProfileResponse != null) {
                return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, userGameProfileResponse);
            } else {
                // 게임 데이터 오류 시
                return new CardanoNftDefaultResponse(RETURN_CODE.BAD_REQUEST);
            }

        } catch (Exception e) {
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
    }

    /**
     * 게임 입장 시 로그 토큰 지불. 10로그 일괄.
     *
     * @return
     */
    @RequestMapping(value = "/insertLogToken", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse insertLogToken(
            @RequestBody InsertTokenRequest insertTokenRequest
    ) throws Exception {
        try {
            boolean logInsertSuccess = gameService.insertLogToken(
                    insertTokenRequest.getRoomName(), insertTokenRequest.getBlueTeam(), insertTokenRequest.getRedTeam()
            );
            if (logInsertSuccess) {
                return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
            } else {
                return new CardanoNftDefaultResponse(RETURN_CODE.INSUFFICIENT_LOG_TOKEN);
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    // 점수판
    @RequestMapping(value = "/score", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse setScore(
            @RequestHeader(value = "token") String token,
            @RequestBody ScoreRequest scoreRequest) throws Exception {

        String gameHash = scoreRequest.getScoreData();
        // 게임 데이터 비교
        //  token + gameId + score
        boolean matchesCheck = bCryptPasswordEncoder.matches(scoreRequest.dateTime + token + scoreRequest.getGameId() + scoreRequest.getScore(), gameHash);
        try {
            if (matchesCheck) {
                // 게임 데이터 정합시
                gameService.setGameScore(token, scoreRequest.getGameId(), scoreRequest.getScore(), gameHash);
                return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
            } else {
                // 게임 데이터 오류 시
                return new CardanoNftDefaultResponse(RETURN_CODE.GAME_HASH_MATCH_ERROR);
            }
        } catch (Exception e) {
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/calculate", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse calculateGameToken(
            @RequestHeader(value = "token") String token,
            @RequestBody CalculateTokenRequest calculateTokenRequest) throws Exception {

        try {
            boolean gameCalculateResult = gameService.calculateGameToken(calculateTokenRequest.getRoomId(), calculateTokenRequest.getTeam());
            if (gameCalculateResult) {
                return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
            } else {
                return new CardanoNftDefaultResponse(RETURN_CODE.BAD_REQUEST);
            }


        } catch (Exception e) {
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
    }


    @RequestMapping(value = "/score", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getScore(
            @RequestParam(value = "gameId") int gameId) throws Exception {
        GameScoreResponse gameScoreResponse = gameService.getGameScoreList(gameId);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameScoreResponse);
    }

    @RequestMapping(value = "/log-score-history", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getLogScoreHistory(
            @RequestHeader(value = "token") String token, @RequestParam(value="page") int page) throws Exception {
        // todo:
        GameScoreHistoryResponse gameScoreHistoryResponseList = gameService.getGameScoreWithLogHistory(token, page);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameScoreHistoryResponseList);
    }

    @RequestMapping(value = "/context", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getUnityContext(
            @RequestParam(value = "game", required = false) String gameName) {

        GameContextResponse gameContextResponse = gameService.getUnityContext(gameName);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameContextResponse);
    }

    @RequestMapping(value = "/token/address", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getTokenAddress(
            @RequestHeader(value = "token", required = false) String token
    ) throws Exception {

        AuthAdaRequest authAdaRequest = new AuthAdaRequest();
        UserGameProfileResponse userGameProfileResponse = gameService.getUserGameProfile(token);
        authAdaRequest.setAuthType("2");
        authAdaRequest.setUserId(userGameProfileResponse.getUserId());
        CardanoAddressEntity cardanoAddressEntity = cardanoAddressRepository.findRandomOneAddress();
        AuthAdaResponse authAdaResponse = new AuthAdaResponse();
        authAdaResponse.setAddress(cardanoAddressEntity.getAddressId());
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, authAdaResponse);
    }

    @RequestMapping(value = "/context/list", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getUnityContextList() {
        List<GameContextResponse> gameContextResponseList = gameService.getUnityContextList();
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameContextResponseList);
    }


}
