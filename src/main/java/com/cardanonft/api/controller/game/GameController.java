package com.cardanonft.api.controller.game;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.request.VillageListRequest;
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

    @RequestMapping(value = "/context", method = RequestMethod.GET)
    @ResponseBody
    public CardanoNftDefaultResponse getUnityContext(
            @RequestHeader(value = "token", required = false) String token,
            @RequestParam(value = "game", required = false) String gameName) {

        GameContextResponse gameContextResponse = gameService.getUnityContext(gameName);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, gameContextResponse);
    }
}
