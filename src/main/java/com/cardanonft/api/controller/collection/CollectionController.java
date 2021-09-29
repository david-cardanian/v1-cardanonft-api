package com.cardanonft.api.controller.collection;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.entity.CardanoNftEntity;
import com.cardanonft.api.repository.CardanoNftRepository;
import com.cardanonft.api.request.CollectionSearchRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProductList(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftRepository.findAllByIsEnabledOrderByCreatedAtDesc("1"));
    }

    @RequestMapping(value = "/{nftId}", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProduct(@PathVariable("nftId") int nftId) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, cardanoNftRepository.findTopByNftIdAndIsEnabledOrderByCreatedAtDesc(nftId, "1"));
    }

    @RequestMapping(value = "/remain/all", method = RequestMethod.POST)
    @ResponseBody
    @CrossOrigin(origins = "*") // 컨트롤러에서 설정
    public CardanoNftDefaultResponse getNFTRemain() throws Exception {
        List<CardanoNftEntity> cardanoNftEntityList = cardanoNftRepository.findAllByIsEnabledOrderByCreatedAtDesc("1");
        Map<Integer, Integer> remainMaps = cardanoNftEntityList.stream()
                .collect(Collectors.toMap(
                        CardanoNftEntity::getNftId,
                        remainCount -> remainCount.getTargetQuantity() - remainCount.getMintCount()
                ));

//        Integer remain = cardanoNftEntity.getTargetQuantity() - cardanoNftEntity.getMintCount();
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, remainMaps);
    }
}
