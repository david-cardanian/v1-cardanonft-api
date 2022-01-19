package com.cardanonft.api.controller.auth;

import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.entity.PasswordAuthCodeEntity;
import com.cardanonft.api.entity.UserEntity;
import com.cardanonft.api.exception.CustomBadCredentialException;
import com.cardanonft.api.exception.CustomBadRequestException;
import com.cardanonft.api.repository.UserRepository;
import com.cardanonft.api.request.SignUpRequest;
import com.cardanonft.api.request.auth.FindAccountVO;
import com.cardanonft.api.request.auth.LoginVO;
import com.cardanonft.api.response.CardanoNftDefaultResponse;
import com.cardanonft.api.response.auth.LoginVOResponse;
import com.cardanonft.api.service.AuthService;
import com.cardanonft.api.util.AccountUtil;
import com.cardanonft.api.vo.auth.AuthToken;
import com.mysql.cj.core.util.StringUtils;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@CrossOrigin("*")
@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthService authService;
    @Autowired
    AccountUtil accountUtil;
    @Autowired
    UserRepository userRepository;

    private static Logger logger = LoggerFactory.getLogger(AuthController.class);


    private String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "로그인API", response = LoginVOResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse login(
            HttpServletRequest request,
            @RequestHeader("key") String apiKey,
            @RequestHeader(value = "uuid", required = false) String uuid,
            @RequestBody LoginVO loginVO) throws Exception{
        logger.debug("login api start");
        try {
            logger.debug(loginVO.getId());
            // 2. 토큰을 발급한다.
            String ip = getIpAddress(request);
            AuthToken token = authService.issueNAccessToken(
                    loginVO.getId(),
                    apiKey,
                    ip,
                    loginVO.getOs_type(),
                    uuid);
            authService.login(token, loginVO);
            token.setReferer(request.getHeader("referer"));
            logger.debug("login api end");
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, token);
        }catch (CustomBadRequestException e) {
            throw e;
        } catch (Exception e) {
            logger.error("error", e);
            return new CardanoNftDefaultResponse(RETURN_CODE.ERROR, e);
        }
    }
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "로그out", response = LoginVOResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse logout(
            HttpServletRequest request,
            @RequestHeader("token") String token,
            @RequestHeader(value = "uuid", required = false) String uuid) throws Exception{
            authService.logout(token);
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS, token);
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "회원 가입", response = LoginVOResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse signup(
            @RequestBody SignUpRequest signUpRequest) throws Exception{
        // 회원가입
        if(StringUtils.isNullOrEmpty(signUpRequest.getId())
                || StringUtils.isNullOrEmpty(signUpRequest.getPassword())
        ){
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        authService.signUp(signUpRequest);
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
    @RequestMapping(value = {"/sendAuthCode", "/sendAuthCode/{checkWithId}"}, method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "인증코드 발송", response = LoginVOResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse sendAuthCode(@PathVariable(value = "checkWithId", required = false) String checkWithId,
                                              @RequestBody FindAccountVO findAccountVO) throws Exception {

        if(checkWithId != null && checkWithId.equals("checkWithId")) {
            boolean userFindResult = authService.sendAuthCodeWithId(findAccountVO.getId());
            if (!userFindResult) {
                throw new CustomBadRequestException(RETURN_CODE.NO_ACCOUNT);
            }
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
        }
        boolean isUserExisted =
                userRepository.existsUserEntityByUserIdAndIsEnabled(findAccountVO.getId(), "1");
        if(!isUserExisted){
            boolean result = authService.sendAuthCode( findAccountVO.getId());
            if(!result){
                logger.info("비밀번호 인증코드 발송시 오류 발생");
                throw new CustomBadRequestException(RETURN_CODE.ERROR);
            }
        }else{
            throw new CustomBadRequestException(RETURN_CODE.ID_DUPLICATION);
        }

        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }

    @RequestMapping(value = {"/confirmAuthCode"}, method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "인증코드 확인", response = LoginVOResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse confirmAuthCode(
            @PathVariable(value = "sendPassword", required = false) String sendPassword,
            @RequestBody FindAccountVO findAccountVO) throws Exception {
        PasswordAuthCodeEntity passwordAuthCodeEntity = authService.findAuthCode(findAccountVO.getId());
        if(passwordAuthCodeEntity == null){
            throw new CustomBadRequestException(RETURN_CODE.EXPIRE_CODE);
        }
        if(!StringUtils.isNullOrEmpty(passwordAuthCodeEntity.getAuthCode()) && passwordAuthCodeEntity.getAuthCode().equals(findAccountVO.getAuthCode())){
            if(passwordAuthCodeEntity.getFailCount() >= 3){
                // 입력오류 횟수가 3회를 넘었으니 인증코드 비활성화.
                authService.disableAuthCode(passwordAuthCodeEntity);
                throw new CustomBadRequestException(RETURN_CODE.EXCEED_AUTH_CODE_FAIL_COUNT);
//                response.setReturnMessage("인증코드 입력오류 회수가 3회를 초과했습니다. 인증코드를 다시 발송하시기 바랍니다.");
            }else{
                // 3번 전에 인증 되었으니까.
                authService.disableAuthCode(passwordAuthCodeEntity);
            }
        }else if(!StringUtils.isNullOrEmpty(passwordAuthCodeEntity.getAuthCode()) && !passwordAuthCodeEntity.getAuthCode().equals(findAccountVO.getAuthCode())){
            if(passwordAuthCodeEntity.getFailCount() >= 3){
                authService.disableAuthCode(passwordAuthCodeEntity);
                throw new CustomBadRequestException(RETURN_CODE.EXCEED_AUTH_CODE_FAIL_COUNT);
            }else {
                authService.authCodeFailCountAdd(passwordAuthCodeEntity);
                throw new CustomBadRequestException(RETURN_CODE.WRONG_CODE);
            }
        }else{
            throw new CustomBadRequestException(RETURN_CODE.ERROR);
        }
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }

    @RequestMapping(value={"/nicknameCheck", "/nicknameCheck/{modify}"}, method = RequestMethod.POST)
    @ApiOperation(httpMethod = "POST", value = "닉네임 확인", response = LoginVOResponse.class)
    @ResponseBody
    public CardanoNftDefaultResponse nicknameCheck(@PathVariable(value = "modify", required = false) String modify,
                                                   @RequestBody SignUpRequest signUpRequest) throws Exception{
        if(StringUtils.isNullOrEmpty(signUpRequest.getNickname())){
            throw new CustomBadRequestException(RETURN_CODE.BAD_REQUEST);
        }
        if(modify != null && modify.equals("modify")) {
            UserEntity userEntity = userRepository.findTopByNickNameAndIsEnabled(signUpRequest.getNickname(), "1");
            if(userEntity != null && !(signUpRequest.getId().equals(userEntity.getUserId()))){
                throw new CustomBadRequestException(RETURN_CODE.ID_DUPLICATION);
            }
            return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
        }
        boolean isNicknameExisted = authService.nicknameCheck(signUpRequest.getNickname());
        if(isNicknameExisted) {
            throw new CustomBadRequestException(RETURN_CODE.ID_DUPLICATION);
        }
        return new CardanoNftDefaultResponse(RETURN_CODE.SUCCESS);
    }
}
