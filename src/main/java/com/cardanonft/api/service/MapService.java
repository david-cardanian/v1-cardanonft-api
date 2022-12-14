package com.cardanonft.api.service;

import com.cardanonft.api.constants.AWS_INFO;
import com.cardanonft.api.constants.C;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.AuthDao;
import com.cardanonft.api.entity.*;
import com.cardanonft.api.exception.CustomBadCredentialException;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.exception.CustomWrongPasswordException;
import com.cardanonft.api.repository.*;
import com.cardanonft.api.request.AssetDeployRequest;
import com.cardanonft.api.request.AssetUnDeployRequest;
import com.cardanonft.api.request.UserImageUploadRequest;
import com.cardanonft.api.request.auth.LoginVO;
import com.cardanonft.api.util.AccountUtil;
import com.cardanonft.api.util.AuthUtil;
import com.cardanonft.api.vo.account.Users;
import com.cardanonft.api.vo.auth.AuthToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.*;

@Service
public class MapService {

    private static Logger logger = LoggerFactory.getLogger(MapService.class);
    @Autowired
    MapParcelRepository mapParcelRepository;
    @Autowired
    CardanoAssetRepository cardanoAssetRepository;
    @Autowired
    CardanoNftRepository cardanoNftRepository;
    @Autowired
    FileUploadService fileUploadService;
    /// 맵에 빌리지 올리기
    public void deployAsset(AssetDeployRequest assetDeployRequest) throws Exception {
        // map parcel user search
        MapParcelEntity mapParcelEntity = mapParcelRepository.findTopByMapParcelIdAndUserIdAndIsEnabled(assetDeployRequest.getMapParcelId(), assetDeployRequest.getUserId(), "1");
        if(mapParcelEntity == null){
            // userId에 매핑되어있는 Map이 존재하지 않을 경우
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        // asset 조회
        CardanoAssetEntity cardanoAssetEntity = cardanoAssetRepository.findTopByAssetIdAndIsEnabled(assetDeployRequest.getAssetId(), "1");
        if(mapParcelEntity == null){
            // asset이 존재하지 않을 경우
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        // nft 조회
        CardanoNftEntity cardanoNftEntity = cardanoNftRepository.findTopByParam1AndParam2AndParam3AndIsEnabledOrderByCreatedAtDesc(String.valueOf(cardanoAssetEntity.getVillageNumber()),cardanoAssetEntity.getContinentId(),cardanoAssetEntity.getVillageId(), "1");
        if(cardanoNftEntity == null){
            // nft가 존재하지 않을 경우
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        // map에 nft 배치
        mapParcelEntity.setVillageNftId(cardanoNftEntity.getNftId());
        mapParcelEntity.setVillageDirection(assetDeployRequest.getVillageDirection());
        mapParcelRepository.save(mapParcelEntity);
    }
    /// 맵에 빌리지 해제
    public void undeployAsset(AssetUnDeployRequest assetUnDeployRequest) throws Exception {
        // map parcel user search
        MapParcelEntity mapParcelEntity = mapParcelRepository.findTopByMapParcelIdAndUserIdAndIsEnabled(assetUnDeployRequest.getMapParcelId(), assetUnDeployRequest.getUserId(), "1");
        if(mapParcelEntity == null){
            // userId에 매핑되어있는 Map이 존재하지 않을 경우
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        // map에 nft 배치해제
        mapParcelEntity.setVillageNftId(0);
        mapParcelEntity.setVillageDirection("3");
        mapParcelRepository.save(mapParcelEntity);
    }
    /// 맵에 빌리지 올리기
    public String uploadImage(UserImageUploadRequest userImageUploadRequest) throws Exception {
        // map parcel user search
        MapParcelEntity mapParcelEntity = mapParcelRepository.findTopByMapParcelIdAndUserIdAndIsEnabled(userImageUploadRequest.getMapParcelId(), userImageUploadRequest.getUserId(), "1");
        if(mapParcelEntity == null){
            // userId에 매핑되어있는 Map이 존재하지 않을 경우
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        String imgURL = null;
        for(MultipartFile file : userImageUploadRequest.getFiles()) {
            if (com.amazonaws.util.StringUtils.isNullOrEmpty(file.getOriginalFilename())) continue;
            imgURL = fileUploadService.uploadImageFileWithThumb(file, AWS_INFO.FOLDER_NAME_USER_IMAGE, true);
            break;
        }
        // map에 image 배치
        mapParcelEntity.setUserImgUrl(imgURL);
        mapParcelEntity.setUserImgUrlThumb(imgURL+"_thumb");
        mapParcelEntity.setVillageDirection(userImageUploadRequest.getVillageDirection());
        mapParcelRepository.save(mapParcelEntity);
        return imgURL;
    }

    public String setAllImages(UserImageUploadRequest userImageUploadRequest) throws Exception {
        List<MapParcelEntity> mapParcelEntityList = new ArrayList<>();
        // map parcels들을 전부 찾아옴.
        if(userImageUploadRequest.getVillageId().equals("All")){
            mapParcelEntityList = mapParcelRepository.findAllByUserIdAndIsEnabled(userImageUploadRequest.getUserId(), "1");
        } else {
            mapParcelEntityList = mapParcelRepository.findAllByUserIdAndVillageIdAndIsEnabled(userImageUploadRequest.getUserId(), userImageUploadRequest.getVillageId(),"1");
        }

        if(mapParcelEntityList == null){
            // userId에 매핑되어있는 Map이 존재하지 않을 경우
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        String imgURL = null;
        for(MultipartFile file : userImageUploadRequest.getFiles()) {
            if (com.amazonaws.util.StringUtils.isNullOrEmpty(file.getOriginalFilename())) continue;
            imgURL = fileUploadService.uploadImageFileWithThumb(file, AWS_INFO.FOLDER_NAME_USER_IMAGE, true);
            break;
        }
        List<MapParcelEntity> imgUpMapParcelList = new ArrayList<>();
        // map에 image 배치
        for ( MapParcelEntity eachMapParcel : mapParcelEntityList ) {
            eachMapParcel.setUserImgUrl(imgURL);
            eachMapParcel.setUserImgUrlThumb(imgURL+"_thumb");
            eachMapParcel.setVillageDirection(userImageUploadRequest.getVillageDirection());
            imgUpMapParcelList.add(eachMapParcel);
        }

        mapParcelRepository.saveAll(imgUpMapParcelList);
        return imgURL;
    }
}
