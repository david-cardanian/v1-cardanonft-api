package com.cardanonft.api.controller.game;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.request.VillageListRequest;
import com.cardanonft.api.request.game.TestRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.service.AuthService;
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

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


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
}
