package com.cardanonft.api.controller.game;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.request.VillageListRequest;
import com.cardanonft.api.request.game.ScoreRequest;
import com.cardanonft.api.request.game.TestRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.response.game.GameContextResponse;
import com.cardanonft.api.service.AuthService;
import com.cardanonft.api.service.GameService;
import com.cardanonft.api.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/game")
public class GameController {
    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final GameService gameService;

    public GameController(BCryptPasswordEncoder bCryptPasswordEncoder, GameService gameService) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.gameService = gameService;
    }


    @RequestMapping(value = "/test", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getVillageList(
            @RequestHeader(value = "token", required = false) String token,
            @RequestBody TestRequest testRequest
    ) throws Exception {

        String gameHash = testRequest.getTestStr();
        String nowTimeMills = DateUtil.getNowDateUTC();

        boolean matehcCheck = bCryptPasswordEncoder.matches(nowTimeMills + token, gameHash);
        System.out.println(nowTimeMills + token);

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, matehcCheck);
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
        System.out.println(scoreRequest);
        try {
            if (matchesCheck) {
                // 게임 데이터 정합시
                System.out.println("pass");

//                gameService.setGameScore(token, scoreRequest.getGameId(), scoreRequest.getScore(), gameHash);
                return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
            } else {
                // 게임 데이터 오류 시
                return new CardanoNftDefaultResponse(RETURN_CODE.GAME_HASH_MATCH_ERROR);
            }
        }catch (Exception e ) {
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/score", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getScore(
            @RequestHeader(value = "token", required = false) String token,
            @RequestParam("game-id") int gameId) {
        // todo: 게임 스코어 보기.
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }

    @RequestMapping(value = "/context", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getUnityContext(
            @RequestHeader(value = "token", required = false) String token,
            @RequestParam(value = "game", required = false) String gameName) {

        GameContextResponse gameContextResponse = gameService.getUnityContext(gameName);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameContextResponse);
    }

    @RequestMapping(value = "/context/list", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getUnityContextList() {
        List<GameContextResponse> gameContextResponseList = gameService.getUnityContextList();
        System.out.println(gameContextResponseList);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameContextResponseList);
    }
}
