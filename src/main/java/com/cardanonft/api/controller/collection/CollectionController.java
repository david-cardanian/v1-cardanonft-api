package com.cardanonft.api.controller.collection;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.repository.CardanoNftRepository;
import com.cardanonft.api.request.CollectionSearchRequest;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/collection")
public class CollectionController {

    private static Logger logger = LoggerFactory.getLogger(CollectionController.class);
    @Autowired
    CardanoNftRepository cardanoNftRepository;

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProductList(
            @RequestBody CollectionSearchRequest collectionSearchRequest
    ) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS,cardanoNftRepository.findAllByIsEnabledOrderByCreatedAtDesc("1"));
    }
    @RequestMapping(value = "/{nftId}", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getProduct(@PathVariable("nftId") int nftId) throws Exception {
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS,cardanoNftRepository.findTopByNftIdAndIsEnabledOrderByCreatedAtDesc(nftId,"1"));
    }
}
