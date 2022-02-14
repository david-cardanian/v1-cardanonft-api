package com.cardanonft.api.controller.collection;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.entity.CardanoAuctionEntity;
import com.cardanonft.api.entity.CardanoNftCollectionEntity;
import com.cardanonft.api.entity.CardanoNftEntity;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.request.CollectionSearchRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.vo.collection.CollectionAddressVO;
import com.cardanonft.api.vo.collection.CollectionHistoryVO;
import com.cardanonft.api.vo.collection.AuctionCollectionVO;
import com.cardanonft.api.vo.collection.CollectionListVO;
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

    // 단건 컬렉션 정보 조회
    @RequestMapping(value = "/collectionInfo", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getCollectionInfo(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftCollectionRepository.findAllByCollectionIdOrderByCreatedAtDesc(collectionSearchRequest.getCollectionId()));
    }

    // 랜덤 주소 조회
    @RequestMapping(value = "/randomAddress", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getRandomAddress(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        CollectionAddressVO collectionAddressVO = new CollectionAddressVO();
        collectionAddressVO.setCollectionId(collectionSearchRequest.getCollectionId());

        List<CollectionListVO> auctionList = collectionDao.getRandomAddress(collectionAddressVO);

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, auctionList);
    }

    @RequestMapping(value = "/listSearch", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getListSearch(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        CollectionListVO collectionListVO = new CollectionListVO();
        collectionListVO.setCollectionId(collectionSearchRequest.getCollectionId());
        collectionListVO.setParam4(collectionSearchRequest.getParam4());
        collectionListVO.setParam5(collectionSearchRequest.getParam5());
        collectionListVO.setKeyword(collectionSearchRequest.getKeyword());
        collectionListVO.setPriority(collectionSearchRequest.getPriority());

        List<CollectionListVO> auctionList = collectionDao.getListSearch(collectionListVO);

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, auctionList);
    }

    @RequestMapping(value = "/auctionList", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getAuctionList(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        AuctionCollectionVO auctionCollectionVO = new AuctionCollectionVO();
        auctionCollectionVO.setProjectId(collectionSearchRequest.getProjectId());

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
                auctionCollectionVO.setStartDate(data.getStartDate());
                break;
            } else {
                // 진행중인건 있으면 현재 날짜로 셋팅
                if(today.compareTo(data.getStartDate()) > 0 && today.compareTo(data.getEndDate()) < 0){
                    auctionCollectionVO.setStartDate(today);
                    break;
                }
                if(today.compareTo(data.getEndDate()) > 0){
                    auctionCollectionVO.setStartDate(today);
                    break;
                }
            }
        }


        List<AuctionCollectionVO> auctionList = collectionDao.getAuctionList(auctionCollectionVO);
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
    @RequestMapping(value = "/remain/{nftCollectionId}", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getNFTCollectionRemain(@PathVariable("nftCollectionId") int nftCollectionId) throws Exception {
        CardanoNftCollectionEntity cardanoNftCollectionEntity = cardanoNftCollectionRepository.findTopByCollectionIdOrderByCreatedAtDesc(nftCollectionId);

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftCollectionEntity.getTargetQuantity() - cardanoNftCollectionEntity.getMintCount());
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
