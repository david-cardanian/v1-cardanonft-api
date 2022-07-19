package com.cardanonft.api.controller.user;

import com.cardanonft.api.constants.CommonConstants;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.entity.*;
import com.cardanonft.api.entity.enums.WhitelistSnapshotType;
import com.cardanonft.api.entity.enums.WhitelistType;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.repository.CardanoTranExecRepository;
import com.cardanonft.api.repository.NftWhitelistCandidateRepository;
import com.cardanonft.api.repository.NftWhitelistRepository;
import com.cardanonft.api.repository.NftWhitelistSnapshotRepository;
import com.cardanonft.api.request.CollectionSearchRequest;
import com.cardanonft.api.request.PasswordModifyRequest;
import com.cardanonft.api.request.UserModifyRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.response.auth.UserProfileVOResponse;
import com.cardanonft.api.service.AuthService;
import com.cardanonft.api.vo.collection.CollectionAddressVO;
import com.cardanonft.api.vo.collection.CollectionListVO;
import com.cardanonft.api.vo.whitelist.WhitelistVO;
import com.mysql.cj.core.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/whitelist")
public class WhiteListController {
    @Autowired
    NftWhitelistRepository nftWhitelistRepository;
    @Autowired
    NftWhitelistSnapshotRepository nftWhitelistSnapshotRepository;
    @Autowired
    NftWhitelistCandidateRepository nftWhitelistCandidateRepository;
    @Autowired
    CardanoTranExecRepository cardanoTranExecRepository;
    @Autowired
    CollectionDao collectionDao;
    @Autowired
    ModelMapper modelMapper;

    private static Logger logger = LoggerFactory.getLogger(WhiteListController.class);

    @RequestMapping(value ="/check/{stakeAddress}", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "whiteList 확인")
    @ResponseBody
    public CardanoNftDefaultResponse WhitelistCheck (@PathVariable("stakeAddress") String stakeAddress) throws Exception {
        NftWhitelistEntity nftWhitelistEntity = nftWhitelistRepository.findTopByStakeAddressAndTypeInAndDeleted(stakeAddress, CommonConstants.WHITE_LIST_KEY_ROMAIN, 0);
        if(nftWhitelistEntity != null && !StringUtils.isNullOrEmpty(nftWhitelistEntity.getStakeAddress())){
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, nftWhitelistEntity);
        }else{
            BigDecimal totalCount = nftWhitelistCandidateRepository.countAllByTypeAndDeleted(WhitelistType.ROMAIN_2ND,0);
            List<NftWhitelistCandidateEntity> candidateEntityList = nftWhitelistCandidateRepository.findTopByStakeAddressAndTypeAndDeleted(stakeAddress, WhitelistType.ROMAIN_2ND,0);
            BigDecimal luck = BigDecimal.ZERO;
            if(candidateEntityList != null && candidateEntityList.size() > 0){
                luck = new BigDecimal(3750).multiply(new BigDecimal(100)).multiply(new BigDecimal(candidateEntityList.size())).divide(totalCount,4, RoundingMode.CEILING);
            }
            WhitelistVO whitelistVO = new WhitelistVO();
            whitelistVO.setLuck(luck.toString());
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, whitelistVO);
        }
    }
    @RequestMapping(value ="/landWhitelist/{stakeAddress}", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "whiteList 확인")
    @ResponseBody
    public CardanoNftDefaultResponse landWhitelistCheck (@PathVariable("stakeAddress") String stakeAddress) throws Exception {
//        List<NftWhitelistSnapshotEntity> nftWhitelistSnapshotList = nftWhitelistSnapshotRepository.findByStakeAddressAndIsEnabledAndWhitelistType(stakeAddress, "1", WhitelistSnapshotType.ROMAIN_LAND_1ST);
        Map returnMap = new HashMap();
        returnMap.put("first", nftWhitelistSnapshotRepository.findByStakeAddressAndIsEnabledAndWhitelistType(stakeAddress, "1", WhitelistSnapshotType.ROMAIN_LAND_1ST));
        returnMap.put("second", nftWhitelistSnapshotRepository.findByStakeAddressAndIsEnabledAndWhitelistType(stakeAddress, "1", WhitelistSnapshotType.ROMAIN_LAND_2ND));

        List<CardanoTranExecEntity> firstTranExecEntityList = cardanoTranExecRepository.findAllByToStakeAddressAndCollectionIdAndIsEnabled(stakeAddress, 54, "1");
        List<CardanoTranExecEntity> secondTranExecEntityList = cardanoTranExecRepository.findAllByToStakeAddressAndCollectionIdAndIsEnabled(stakeAddress, 56, "1");
        int firstExecMintCount = 0;
        int secondExecMintCount = 0;
        for(CardanoTranExecEntity mintTranExec : firstTranExecEntityList){
            firstExecMintCount += mintTranExec.getMintingCount();
        }
        for(CardanoTranExecEntity mintTranExec : secondTranExecEntityList){
            secondExecMintCount += mintTranExec.getMintingCount();
        }

        returnMap.put("firstBuy", firstExecMintCount);
        returnMap.put("secondBuy", secondExecMintCount);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, returnMap);
    }
    @RequestMapping(value ="/draw/address", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "whiteList 확인")
    @ResponseBody
    public CardanoNftDefaultResponse drawAddress (
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
                CollectionAddressVO collectionAddressVO = new CollectionAddressVO();
        collectionAddressVO.setCollectionId(collectionSearchRequest.getCollectionId());
        List<CollectionListVO> addressList = collectionDao.getRandomAddress(collectionAddressVO);
        if(addressList != null && addressList.size() > 0){
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, addressList.get(0));
        }else{
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, null);
        }
    }
}
