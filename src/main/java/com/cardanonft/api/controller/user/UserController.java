package com.cardanonft.api.controller.user;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.MapDao;
import com.cardanonft.api.entity.PasswordAuthCodeEntity;
import com.cardanonft.api.exception.CustomBadCredentialException;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.repository.MapParcelRepository;
import com.cardanonft.api.request.*;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.response.auth.UserProfileVOResponse;
import com.cardanonft.api.service.AuthService;
import com.cardanonft.api.service.MapService;
import com.mysql.cj.core.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin("*")
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    AuthService authService;

    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(value ="/profile", method = RequestMethod.GET)
    @ApiOperation(httpMethod = "GET", value = "회원 정보 수정")
    @ResponseBody
    public CardanoNftDefaultResponse userProfile (@RequestHeader("token") String token) throws Exception {
        // 토큰으로 user 확인
        authService.verifyToken(token);
        UserProfileVOResponse userProfileVOResponse = authService.findUserProfile(token);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, userProfileVOResponse);
    }

    @RequestMapping(value ="/modify", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "회원 정보 수정")
    @ResponseBody
    public CardanoNftDefaultResponse userModify(
            @RequestHeader("token") String token,
            @RequestBody UserModifyRequest userModifyRequest)  throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,userModifyRequest.getId());
        if(StringUtils.isNullOrEmpty(userModifyRequest.getId())
        ){
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        authService.userModify(userModifyRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
    @RequestMapping( value = "/modifyPassword", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "회원 비밀번호 수정")
    @ResponseBody
    public CardanoNftDefaultResponse userPasswordModify(
            @RequestHeader("token") String token,
            @RequestBody PasswordModifyRequest passwordModifyRequest) throws Exception {
        // 토큰으로 user 확인
        authService.verifyTokenWithId(token,passwordModifyRequest.getId());
        if(StringUtils.isNullOrEmpty(passwordModifyRequest.getId())
//                || StringUtils.isNullOrEmpty(passwordModifyRequest.getOldPassword())
                || StringUtils.isNullOrEmpty(passwordModifyRequest.getNewPassword())
        ){
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
//        boolean comparePassword = authService.comparePassword(passwordModifyRequest.getId(), passwordModifyRequest.getOldPassword());
//        if(!comparePassword){
//            throw new CustomBadCredentialException(RETURN_CODE.WRONG_OLD_PASSWORD);
//        }
        authService.resetPassword(passwordModifyRequest.getId(),passwordModifyRequest.getNewPassword());

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
    @RequestMapping( value = "/modifyLostPassword", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "회원 비밀번호 분실/수정")
    @ResponseBody
    public CardanoNftDefaultResponse userPasswordModifyLost(
            @RequestHeader("authCode") String authCode,
            @RequestBody PasswordModifyRequest passwordModifyRequest) throws Exception {
        // user 확인
        PasswordAuthCodeEntity passwordAuthCodeEntity = authService.findAuthCode(passwordModifyRequest.getId(),authCode);

        if(passwordAuthCodeEntity == null || StringUtils.isNullOrEmpty(passwordModifyRequest.getId())
//                || StringUtils.isNullOrEmpty(passwordModifyRequest.getOldPassword())
                || StringUtils.isNullOrEmpty(passwordModifyRequest.getNewPassword())
        ){
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
//        boolean comparePassword = authService.comparePassword(passwordModifyRequest.getId(), passwordModifyRequest.getOldPassword());
//        if(!comparePassword){
//            throw new CustomBadCredentialException(RETURN_CODE.WRONG_OLD_PASSWORD);
//        }
        authService.resetPassword(passwordModifyRequest.getId(),passwordModifyRequest.getNewPassword());

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
}
