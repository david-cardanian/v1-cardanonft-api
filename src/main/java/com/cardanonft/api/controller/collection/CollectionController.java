package com.cardanonft.api.controller.collection;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.entity.CardanoAuctionEntity;
import com.cardanonft.api.entity.CardanoNftCollectionEntity;
import com.cardanonft.api.entity.CardanoNftEntity;
import com.cardanonft.api.repository.CardanoAuctionRepository;
import com.cardanonft.api.repository.CardanoNftCollectionRepository;
import com.cardanonft.api.repository.CardanoNftRepository;
import com.cardanonft.api.request.CollectionSearchRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.vo.collection.CollectionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/collection")
public class CollectionController {
    @Value("${db.secret.key}")


    private static Logger logger = LoggerFactory.getLogger(CollectionController.class);
    @Autowired
    CardanoNftRepository cardanoNftRepository;
    @Autowired
    CardanoAuctionRepository cardanoAuctionRepository;
    @Autowired
    CardanoNftCollectionRepository cardanoNftCollectionRepository;
    @Autowired
    CollectionDao collectionDao;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProductList(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftRepository.findAllByIsEnabledOrderByCreatedAtDesc("1"));
    }

    @RequestMapping(value = "/auctionList", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getAuctionList(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String today = formatter.format(new Date());

        // 조회기준 날짜 추출
        CardanoAuctionEntity cardanoActionDate = cardanoAuctionRepository.findTopByIsEnabledAndProjectIdOrderByStartDateDesc("1", collectionSearchRequest.getProjectId());

        // 현재날짜와 비교 후 기준 날짜 입력
        if(cardanoActionDate.getStartDate() != today && today.compareTo(cardanoActionDate.getStartDate()) == -1) {
            collectionSearchRequest.setStartDate(cardanoActionDate.getStartDate());
        } else collectionSearchRequest.setStartDate(today);

        List<CollectionSearchRequest> auctionList = collectionDao.getAuctionList(collectionSearchRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, auctionList);
    }

    @RequestMapping(value = "/{nftId}", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProduct(@PathVariable("nftId") int nftId) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftRepository.findTopByNftIdAndIsEnabledOrderByCreatedAtDesc(nftId, "1"));
    }

    @RequestMapping(value = "/remain/all", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getNFTRemain() throws Exception {
        List<CardanoNftEntity> cardanoNftEntityList = cardanoNftRepository.findAllByIsEnabledOrderByCreatedAtDesc("1");
        Map<Integer, Integer> remainMaps = cardanoNftEntityList.stream()
                .collect(Collectors.toMap(
                        CardanoNftEntity::getNftId,
                        remainCount -> remainCount.getTargetQuantity() - remainCount.getMintCount()
                ));

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, remainMaps);
    }

    @RequestMapping(value = "/remain/each/{nftId}", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getNFTRemainEach(@PathVariable("nftId") int nftId) throws Exception {
        CardanoNftEntity cardanoNftEntityList = cardanoNftRepository.findTopByNftIdAndIsEnabledOrderByCreatedAtDesc(nftId, "1");

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftEntityList.getTargetQuantity() - cardanoNftEntityList.getMintCount());
    }

    @RequestMapping(value = "/closed", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse checkClosed() throws Exception {
        List<CardanoNftCollectionEntity> cardanoNftCollectionEntityList =
                cardanoNftCollectionRepository.findAllByProjectIdOrderByCreatedAtDesc(1);
        Map<Integer, String> remainMaps = cardanoNftCollectionEntityList.stream()
                .collect(Collectors.toMap(
                        CardanoNftCollectionEntity::getCollectionId,
                        CardanoNftCollectionEntity::getCloseYn
                ));
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, remainMaps);
    }
}
