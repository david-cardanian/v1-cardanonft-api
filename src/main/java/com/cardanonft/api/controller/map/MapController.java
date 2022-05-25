package com.cardanonft.api.controller.map;

import com.cardanonft.api.constants.AWS_INFO;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.CollectionDao;
import com.cardanonft.api.dao.MapDao;
import com.cardanonft.api.entity.CardanoAuctionEntity;
import com.cardanonft.api.entity.CardanoNftCollectionEntity;
import com.cardanonft.api.entity.CardanoNftEntity;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.request.*;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.service.AuthService;
import com.cardanonft.api.service.FileUploadService;
import com.cardanonft.api.service.MapService;
import com.cardanonft.api.vo.collection.AuctionCollectionVO;
import com.cardanonft.api.vo.collection.CollectionAddressVO;
import com.cardanonft.api.vo.collection.CollectionHistoryVO;
import com.cardanonft.api.vo.collection.CollectionListVO;
import com.mysql.cj.core.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.stream.Collectors;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/map")
public class MapController {
    private static Logger logger = LoggerFactory.getLogger(MapController.class);
    @Autowired
    MapParcelRepository mapParcelRepository;
    @Autowired
    MapDao mapDao;
    @Autowired
    MapService mapService;
    @Autowired
    AuthService authService;

    @RequestMapping(value = "/3d/list", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse get3DParcelList(
            @RequestBody MapSearchRequest mapSearchRequest
    ) throws Exception {
        if(StringUtils.isNullOrEmpty(mapSearchRequest.getContinentId())){
            mapSearchRequest.setContinentId("Byron");
        }
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, mapDao.get3DParcelList(mapSearchRequest));
    }

    @RequestMapping(value = "/asset/deploy", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse deployAsset(
            @RequestHeader("token") String token,
            @RequestBody AssetDeployRequest assetDeployRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,assetDeployRequest.getUserId());
        mapService.deployAsset(assetDeployRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
    @RequestMapping(value = "/asset/undeploy", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse undeployAsset(
            @RequestHeader("token") String token,
            @RequestBody AssetUnDeployRequest assetUnDeployRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,assetUnDeployRequest.getUserId());
        mapService.undeployAsset(assetUnDeployRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
    @RequestMapping(value = "/image/upload", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse uploadImage(
            @RequestHeader("token") String token,
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("userId") String userId,
            @RequestParam("villageDirection") String villageDirection,
            @RequestParam("mapParcelId") Integer mapParcelId
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,userId);
        UserImageUploadRequest userImageUploadRequest = new UserImageUploadRequest();
        userImageUploadRequest.setFiles(files);
        userImageUploadRequest.setUserId(userId);
        userImageUploadRequest.setMapParcelId(mapParcelId);
        userImageUploadRequest.setVillageDirection(villageDirection);
        String url = mapService.uploadImage(userImageUploadRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS,url);
    }
    @RequestMapping(value = "/image/all-images", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse setAllImages(
            @RequestHeader("token") String token,
            @RequestParam("file") List<MultipartFile> files,
            @RequestParam("userId") String userId,
            @RequestParam("villageDirection") String villageDirection,
            @RequestParam("villageId") String villageId
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,userId);
        UserImageUploadRequest userImageUploadRequest = new UserImageUploadRequest();
        userImageUploadRequest.setFiles(files);
        userImageUploadRequest.setUserId(userId);
        userImageUploadRequest.setVillageDirection(villageDirection);
        userImageUploadRequest.setVillageId(villageId);
        String url = mapService.setAllImages(userImageUploadRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS,url);
    }
    @RequestMapping(value = "/parcel/info", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse getParcelInfo(
            @RequestBody MapParcelSearchRequest mapParcelSearchRequest
    ) throws Exception {
        if(StringUtils.isNullOrEmpty(mapParcelSearchRequest.getContinentId())){
            mapParcelSearchRequest.setContinentId("Byron");
        }
        if(StringUtils.isNullOrEmpty(mapParcelSearchRequest.getVillageId())){
            mapParcelSearchRequest.setVillageId("Charles");
        }
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, mapDao.getParcelInfo(mapParcelSearchRequest));
    }
    @RequestMapping(value = "/parcel/moon/toggle", method = RequestMethod.POST)
    @ResponseBody
    public CardanoNftDefaultResponse toggleMoon(
            @RequestHeader("token") String token,
            @RequestBody MoonToggleRequest toggleRequest
    ) throws Exception {
        // 토큰으로 user 확인
        authService.verifyToken(token);
        mapParcelRepository.moonOnoff(toggleRequest.getMapParcelId(),toggleRequest.getMoonOnoff());
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }

}
