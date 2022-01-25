package com.cardanonft.api.controller.account;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.AssetDao;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.entity.CardanoAssetEntity;
import com.cardanonft.api.entity.CardanoNftEntity;
import com.cardanonft.api.entity.MapParcelEntity;
import com.cardanonft.api.entity.UserRolesEntity;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.request.AccountDeleteRequest;
import com.cardanonft.api.request.AccountListRequest;
import com.cardanonft.api.request.MapListRequest;
import com.cardanonft.api.request.VillageListRequest;
import com.cardanonft.api.request.auth.AuthAdaRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.response.auth.AuthAdaResponse;
import com.cardanonft.api.service.AuthService;
import com.mysql.cj.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/account")
public class AccountController {
    @Value("${db.secret.key}")

    private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    CardanoAccountRepository cardanoAccountRepository;
    @Autowired
    CardanoAssetRepository cardanoAssetRepository;
    @Autowired
    CardanoNftRepository cardanoNftRepository;
    @Autowired
    MapParcelRepository mapParcelRepository;
    @Autowired
    UserRolesRepository userRolesRepository;
    @Autowired
    CollectionDao collectionDao;
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getVillageList(
            @RequestHeader("token") String token,
            @RequestBody AccountListRequest accountListRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,accountListRequest.getUserId());
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoAccountRepository.findAllByUserIdAndIsEnabledOrderByCreatedAt(accountListRequest.getUserId(),"1"));
    }

    // add wallet modal data
    @RequestMapping(value = "/auth/ada", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getAuthAda(
            @RequestHeader("token") String token,
            @RequestBody AuthAdaRequest authAdaRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,authAdaRequest.getUserId());
        UserRolesEntity userRolesEntity = userRolesRepository.findTopByUserIdAndIsEnabled(authAdaRequest.getUserId(),"1");
        BigDecimal bigDecimal = new BigDecimal(String.valueOf(userRolesEntity.getUserRoleId())).add(new BigDecimal("2000000")).divide(new BigDecimal("1000000")).setScale(6);
        if(StringUtils.isNullOrEmpty(authAdaRequest.getAuthType())){
            authAdaRequest.setAuthType("1");
        }
        AuthAdaResponse authAdaResponse = collectionDao.getRandomAuthAddress(authAdaRequest);
        authAdaResponse.setAuthAda(bigDecimal.toString());
        // TO-DO 토큰으로 user 확인
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, authAdaResponse);
    }
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getMapList(
            @RequestHeader("token") String token,
            @RequestBody AccountDeleteRequest accountDeleteRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,accountDeleteRequest.getUserId());
        boolean isRight = cardanoAccountRepository.existsCardanoAccountEntityByUserIdAndStakeAddressAndIsEnabled(accountDeleteRequest.getUserId(),accountDeleteRequest.getStakeAddress(),"1");
        if(!isRight){
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
//         map carcel mapping data reset
        List<CardanoAssetEntity> cardanoAssetEntityList = cardanoAssetRepository.findAllByStakeAddressAndIsEnabled(accountDeleteRequest.getStakeAddress(),"1");
        for(CardanoAssetEntity cardanoAssetEntity : cardanoAssetEntityList){
            if(cardanoAssetEntity.getVillageNumber() != null &&  cardanoAssetEntity.getVillageNumber() != 0){
                CardanoNftEntity cardanoNftEntity = cardanoNftRepository.findTopByParam1AndParam2AndParam3AndIsEnabledOrderByCreatedAtDesc(String.valueOf(cardanoAssetEntity.getVillageNumber()),cardanoAssetEntity.getContinentId(),cardanoAssetEntity.getVillageId(), "1");
                List<MapParcelEntity> mapParcelEntityList = mapParcelRepository.findAllByUserIdAndVillageNftIdAndIsEnabled(accountDeleteRequest.getUserId(),cardanoNftEntity.getNftId(),"1");
                for(MapParcelEntity mapParcelEntity : mapParcelEntityList){
                    mapParcelRepository.undeployVillage(mapParcelEntity.getMapParcelId());
                }
            }else if(cardanoAssetEntity.getParcelX() != null && cardanoAssetEntity.getParcelX() > 0
                    && cardanoAssetEntity.getParcelY() != null && cardanoAssetEntity.getParcelY() > 0
            ){
                List<MapParcelEntity> mapParcelEntityList = mapParcelRepository.findAllByUserIdAndParcelX3dAndParcelY3dAndIsEnabled(accountDeleteRequest.getUserId(),cardanoAssetEntity.getParcelX(),cardanoAssetEntity.getParcelY(),"1");
                for(MapParcelEntity mapParcelEntity : mapParcelEntityList){
                    mapParcelRepository.undeployUser(mapParcelEntity.getMapParcelId());
                }
            }
            // asset disabled
            cardanoAssetRepository.updateAssetDisabled(cardanoAssetEntity.getAssetId());
        }
        // account disabled
        cardanoAccountRepository.updateAccoutDisabled(accountDeleteRequest.getStakeAddress());
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
}
