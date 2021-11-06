package com.cardanonft.api.controller.collection;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.entity.CardanoAuctionEntity;
import com.cardanonft.api.entity.CardanoNftCollectionEntity;
import com.cardanonft.api.entity.CardanoNftEntity;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.request.CollectionSearchRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.vo.collection.CollectionHistoryVO;
import com.cardanonft.api.vo.collection.CollectionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
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
    CardanoAuctionDetailRepository cardanoAuctionDetailRepository;
    @Autowired
    CardanoAuctionAddressRepository cardanoAuctionAddressRepository;
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
        CollectionVO collectionVO = new CollectionVO();
        collectionVO.setProjectId(collectionSearchRequest.getProjectId());

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        TimeZone utc = TimeZone.getTimeZone("UTC");
        formatter.setTimeZone(utc);
        String today = formatter.format(new Date());


        // 조회기준 날짜 추출
        List<CardanoAuctionEntity> cardanoActionDate = cardanoAuctionRepository.findAllByIsEnabledAndProjectIdOrderByStartDate("1", collectionSearchRequest.getProjectId());

        // 현재날짜와 비교 후 기준 날짜 입력
        for (int i=0; i < cardanoActionDate.size(); i++) {
            CardanoAuctionEntity data = cardanoActionDate.get(i);

            // 하나도 시작 안했을 경우
            if(data.getStartDate() != today && today.compareTo(data.getStartDate()) < 0) {
                collectionVO.setStartDate(data.getStartDate());
                break;
            } else {
                // 진행중인건 있으면 현재 날짜로 셋팅
                if(today.compareTo(data.getStartDate()) > 0 && today.compareTo(data.getEndDate()) < 0){
                    collectionVO.setStartDate(today);
                    break;
                }
                if(today.compareTo(data.getEndDate()) > 0){
                    collectionVO.setStartDate(today);
                    break;
                }
            }
        }


        List<CollectionVO> auctionList = collectionDao.getAuctionList(collectionVO);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, auctionList);
    }

    @RequestMapping(value = "/{nftId}", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProduct(@PathVariable("nftId") int nftId) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftRepository.findTopByNftIdAndIsEnabledOrderByCreatedAtDesc(nftId, "1"));
    }

    @RequestMapping(value = "/auctionDetail", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getAuctionDetail(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoAuctionDetailRepository.findTopByIsEnabledAndAuctionDetailId("1", collectionSearchRequest.getAuctionDetailId()));
    }

    @RequestMapping(value = "/auctionAddress", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getAuctionAddress(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoAuctionAddressRepository.findTopByIsEnabledAndNftCollectionId("1", collectionSearchRequest.getCollectionId()));
    }

    @RequestMapping(value = "/auctionHistory", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getAuctionHistory(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        CollectionHistoryVO collectionHistoryVO = new CollectionHistoryVO();
        collectionHistoryVO.setAuctionId(collectionSearchRequest.getAuctionId());
        collectionHistoryVO.setAuctionDetailId(collectionSearchRequest.getAuctionDetailId());
        collectionHistoryVO.setCollectionId(collectionSearchRequest.getCollectionId());

        List<CollectionHistoryVO> auctionList = collectionDao.getAuctionHistory(collectionHistoryVO);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, auctionList);
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
