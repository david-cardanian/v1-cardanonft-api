package com.cardanonft.api.controller.asset;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.AssetDao;
import com.cardanonft.api.dao.MapDao;
import com.cardanonft.api.repository.MapParcelRepository;
import com.cardanonft.api.request.MapListRequest;
import com.cardanonft.api.request.MapSearchRequest;
import com.cardanonft.api.request.VillageListRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.service.AuthService;
import com.mysql.cj.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/asset")
public class AssetController {
    @Value("${db.secret.key}")

    private static Logger logger = LoggerFactory.getLogger(AssetController.class);
    @Autowired
    MapParcelRepository mapParcelRepository;
    @Autowired
    AssetDao assetDao;
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/village/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getVillageList(
            @RequestHeader("token") String token,
            @RequestBody VillageListRequest villageListRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,villageListRequest.getUserId());
        if(StringUtils.isNullOrEmpty(villageListRequest.getContinentId())){
            villageListRequest.setContinentId("Byron");
        }
        if(StringUtils.isNullOrEmpty(villageListRequest.getVillageId())){
            villageListRequest.setVillageId("Charles");
        }
        // TO-DO 토큰으로 user 확인
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, assetDao.getVillageList(villageListRequest));
    }
    @RequestMapping(value = "/map/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getMapList(
            @RequestHeader("token") String token,
            @RequestBody MapListRequest mapListRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,mapListRequest.getUserId());
        if(StringUtils.isNullOrEmpty(mapListRequest.getContinentId())) {
            mapListRequest.setContinentId("Byron");
        }
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, assetDao.getMapList(mapListRequest));
    }
}
