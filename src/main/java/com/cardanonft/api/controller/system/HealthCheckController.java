package com.cardanonft.api.controller.system;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/v1/health/")
public class HealthCheckController {


    private static Logger logger = LoggerFactory.getLogger(HealthCheckController.class);

    @RequestMapping(value = "serverStatus", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "서버 상태 API", response = CardanoNftDefaultResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse checkServer() throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
}
