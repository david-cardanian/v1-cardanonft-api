package com.cardanonft.api.service;

import com.cardanonft.api.constants.C;
import com.cardanonft.api.constants.RETURN_CODE;
import com.cardanonft.api.dao.AuthDao;
import com.cardanonft.api.entity.PasswordAuthCodeEntity;
import com.cardanonft.api.entity.UserEntity;
import com.cardanonft.api.entity.UserRolesEntity;
import com.cardanonft.api.exception.CustomBadCredentialException;
import com.cardanonft.api.exception.CustomWrongPasswordException;
import com.cardanonft.api.repository.PasswordAuthCodeRepository;
import com.cardanonft.api.repository.UserRepository;
import com.cardanonft.api.repository.UserRolesRepository;
import com.cardanonft.api.request.SignUpRequest;
import com.cardanonft.api.request.UserModifyRequest;
import com.cardanonft.api.request.auth.*;
import com.cardanonft.api.response.auth.UserProfileVOResponse;
import com.cardanonft.api.util.AccountUtil;
import com.cardanonft.api.util.AuthUtil;
import com.cardanonft.api.vo.account.Users;
import com.cardanonft.api.vo.auth.AuthToken;
import com.cardanonft.api.vo.auth.UserRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.util.*;

@Service
public class AuthService {

    private static Logger logger = LoggerFactory.getLogger(AuthService.class);

    @Autowired
    private AuthDao authDao;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserRolesRepository userRolesRepository;
    @Autowired
    private PasswordAuthCodeRepository passwordAuthCodeRepository;
    @Autowired
    private AuthUtil authUtil;
    @Autowired
    private AccountUtil accountUtil;
    @Autowired
    private MailService mailService;

    @Value("${auth.expired_days}")
    private int expired_days;

    /// 계정 정보 가져오기
    public int getUserIdByAccount(String account) throws Exception {
        return authDao.getUserIdByAccount(account);
    }

    /// 토큰 발급 처리하기
    public AuthToken issueNAccessToken(String userId, String key, String ip, String platform, String uuid)
            throws Exception {

        AuthToken nToken = null;

        /// 모듈 접속 권한 체크
//        List<Authority2> authorities = authDao.getAuthorities(String.valueOf(userId));
        String roleName = C.API_KEY.getAuthorityName(key);
        String clientName = C.API_KEY.getClientName(key);

        /// 권한 체크 후 토큰 발급 절차 진행
//        for (Authority2 authority : authorities) {
//            if (authority.getAuthority().equals(roleName) || !StringUtils.isEmpty(inviteCode) || (C.API_KEY.V3_WEB.equals(key) && C.ROLE_SCHEDULE_MANAGER.equals(authority.getAuthority())) ) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, expired_days);
        /// 토큰 발급 처리
        nToken = authUtil.generateNAccessToken();

        nToken.setExpired_at(calendar.getTime());
        nToken.setIssued_client(clientName);
        nToken.setIssued_ip(ip);
        nToken.setIssued_platform(platform);
        nToken.setUser_id(userId);
        nToken.setUuid(uuid);

//                // 앱 중복 로그인 방지를 위하여 로그인 시 기존 앱 access-token 소멸
//                if(clientName.equals(C.CLIENT_TYPE.QCHAR_APP.getClientType())){
//                    authDao.updateAuthTokenIsEnabled(nToken);
//                }

        //// 토큰 생성
        authDao.insertAuthToken(nToken);


        /// 존재하지 않는 계정 권한이면 익셉션
        if (nToken == null) {
            throw new CustomBadCredentialException(RETURN_CODE.WRONG_ACCOUNT);
        }

        return nToken;
    }


    /// 로그인 검증 프로세스
    public void login(AuthToken token, LoginVO loginVO) throws Exception {
        String userId = token.getUser_id();
        //// 비밀번호를 검증한다.
        Users users = authDao.getPassword(String.valueOf(userId));
        if (users == null) {
            // userId에 매핑되어있는 비밀번호가 존재하지 않을 경우
            throw new CustomBadCredentialException(RETURN_CODE.NO_ACCOUNT);
        } else if (users.getIsLocked() > 0 && !loginVO.getOs_type().equals("WEB")) {
            // 앱이면서 비밀번호 오류 횟수가 5회를 초과 하였을경우
            throw new CustomWrongPasswordException(RETURN_CODE.ACCOUNT_IS_LOCKED);
        } else {
            boolean confirmPassword = false;
            confirmPassword = accountUtil.confirmPasswordEcoded(loginVO.getPassword(), users.getPassword());
//                if (loginVO.getOs_type().equals("iOS") || loginVO.getOs_type().equals("ANDROID") ){
//                    //confirmPassword = accountUtil.confirmPasswordEcoded(loginVO.getPassword(), users.getPassword());
//                    confirmPassword = accountUtil.confirmPassword(loginVO.getPassword(), users.getPassword());
//                }else{
//                    confirmPassword = accountUtil.confirmPasswordEcoded(loginVO.getPassword(), users.getPassword());
//                    confirmPassword = accountUtil.confirmPassword(loginVO.getPassword(), users.getPassword());
//                }
            // 비밀번호를 잘못 입력하였을 경우
            if (!confirmPassword) {

                    throw new CustomBadCredentialException(RETURN_CODE.WRONG_ACCOUNT);
//
//                Timestamp currTs = new Timestamp(new Date().getTime());
//
//                // 계정 잠금이 되지 않았으며, 가장 최근에 오입력한 시간 보다 2시간 이후에 들어온 요청이면 기존 failCount 초기화
//                if (users.getFailExpiredAt() != null) {
//                    if (users.getIsLocked() == 0 && users.getFailExpiredAt().compareTo(currTs) <= 0) {
//                        users.setFailCount(0);
//                    }
//                }
//
//                if (users.getFailCount() < 5 && users.getIsLocked() == 0) {
//                    users.setFailCount(users.getFailCount() + 1);
//
//                    // 비밀번호 오류 회수 초기화 시점 적용 --> 가장 최근 오입력한 시점으로부터 2시간 이후엔 초기화
//                    Calendar cal = Calendar.getInstance();
//                    cal.setTimeInMillis(currTs.getTime());
//                    cal.add(Calendar.HOUR_OF_DAY, C.DEFAULT_VALUES.PASSWORD_FAIL_COUNT_INIT_HOUR);
//
//                    users.setFailExpiredAt(new Timestamp(cal.getTimeInMillis()));
//
//                    if (users.getFailCount() >= 5 && users.getIsLocked() == 0) {
//                        users.setIsLocked((byte) 1);
//                    }
//
//                    // authDao.updatePasswordFailCountAndIsLocked(users);
//                }
//
//                throw new CustomWrongPasswordException(RETURN_CODE.WRONG_ACCOUNT, users.getFailCount(),
//                        users.getIsLocked(), users.getFailExpiredAt());
            }
        }

        if (users.getFailCount() > 0) {
            authDao.resetPasswordFailCountAndIsLocked(String.valueOf(userId));
        }
        //// 토큰을 인증정보에 맞게 업데이트한다.
        token.setIs_logined(1);
        token.setLogined_at(new Date());
        authDao.updateAuthToken(token);
    }

    /// 로그아웃 프로세스
    public void logout(String token) throws Exception {
        AuthToken nToken = new AuthToken();
        nToken.setToken(token);
        nToken.setRefresh_token("");

        authDao.disableAuthToken(nToken);
    }

    public Map<String, Object> getToken(String apiKey, String apiSecret) throws Exception {
        if (StringUtils.isEmpty(apiKey) || StringUtils.isEmpty(apiSecret))
            throw new CustomBadCredentialException(RETURN_CODE.BAD_REQUEST);
        Map<String, Object> params = new HashMap<>();
        params.put("api_key", apiKey);
        params.put("api_secret", apiSecret);

        Map<String, Object> integrationKeysMap = authDao.getToken(params);

        if (integrationKeysMap == null || StringUtils.isEmpty(integrationKeysMap.get("access_token").toString()))
            throw new CustomBadCredentialException(RETURN_CODE.INVALID_AUTHORITY);

        return integrationKeysMap;
    }

    public UserEntity userIdFindNotDuplicated(String userId){
        return userRepository.findTopByUserId(userId);
    }

    public UserEntity userIdCheck(String userId){
        return userRepository.findTopByUserIdAndIsEnabled(userId, "1");
    }

//    public UserEntity mobileCheck(String mobile) {
//        return userRepository.findTopByMobileAndIsEnabled(mobile, "1");
//    }

    public UserEntity emailCheck(String email){
        return userRepository.findTopByEmailAndIsEnabled(email, "1");
    }

    public boolean nicknameCheck(String nickName) {
        return userRepository.existsUserEntityByNickNameAndIsEnabled(nickName, "1");
    }


    public void signUp(SignUpRequest signUpRequest) throws Exception{
        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setUserId(signUpRequest.getId());
            userEntity.setPassword(accountUtil.encodePassword(signUpRequest.getPassword()));
            userEntity.setEmail(signUpRequest.getEmail());
            userEntity.setUserName(signUpRequest.getNickname());
            userEntity.setNickName(signUpRequest.getNickname());
            userEntity.setTermsPrivacy("1");
            userEntity.setTermsUser("1");
            userRepository.save(userEntity);

            UserRolesEntity userRolesEntity = new UserRolesEntity();
            userRolesEntity.setUserId(signUpRequest.getId());
            userRolesEntity.setRoleId(UserRole.USER);
            userRolesRepository.save(userRolesEntity);

            userEntity.setUniqueNumber(userRolesEntity.getUserRoleId());
            userRepository.save(userEntity);
        }catch (Exception e) {
            throw new CustomBadCredentialException(RETURN_CODE.ERROR);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void userModify(UserModifyRequest userModifyRequest) {
        UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(userModifyRequest.getId(), "1");

        if(!StringUtils.isEmpty(userModifyRequest.getNickName())){
            userEntity.setNickName(userModifyRequest.getNickName());
        }
        if(!StringUtils.isEmpty(userModifyRequest.getTwitter())){
            userEntity.setTwitter(userModifyRequest.getTwitter());
        }
        if(!StringUtils.isEmpty(userModifyRequest.getFacebook())){
            userEntity.setFacebook(userModifyRequest.getFacebook());
        }
        if(!StringUtils.isEmpty(userModifyRequest.getDiscord())){
            userEntity.setDiscord(userModifyRequest.getDiscord());
        }

        userRepository.save(userEntity);
    }
//
//    @Transactional(rollbackFor = {Exception.class})
//    public void updateUserPassword(PasswordModifyVO passwordModifyVO){
//        userRepository.updateUserPassword(accountUtil.encodePassword(passwordModifyVO.getNewPassword()), passwordModifyVO.getId());
//    }
//
//    public FindAccountVO findUserId(String email) {
//        FindAccountVO findAccountVO = new FindAccountVO();
//        UserEntity userEntity = userRepository.findTopByEmailAndIsEnabledOrderByCreatedAtDesc(email, "1");
//        if(userEntity != null && !com.mysql.cj.core.util.StringUtils.isNullOrEmpty(userEntity.getUserId())) {
//            findAccountVO.setId(userEntity.getUserId()+"");
//            return findAccountVO;
//        }else {
//            return null;
//        }
//    }
    public boolean sendAuthCodeWithId(String userId) throws Exception {
        boolean isUserExisted =
                userRepository.existsUserEntityByUserIdAndIsEnabled(userId, "1");
        if(isUserExisted){
            int result = new Random().nextInt(1000000) + 100000;
            if (result > 1000000) {
                result = result - 100000;
            }
            mailService.sendAuthCodeMail(String.valueOf(result),userId);
            PasswordAuthCodeEntity passwordAuthCodeEntity = new PasswordAuthCodeEntity();
            passwordAuthCodeEntity.setAuthCode(String.valueOf(result));
            passwordAuthCodeEntity.setFailCount(0);
            passwordAuthCodeEntity.setUserId(userId);
            passwordAuthCodeEntity.setIsEnabled("1");
            passwordAuthCodeRepository.save(passwordAuthCodeEntity);
            return true;
        }
        return false;
    }

    public boolean sendAuthCode(String userId) throws Exception {
        int result = new Random().nextInt(1000000) + 100000;
        if (result > 1000000) {
            result = result - 100000;
        }
        mailService.sendAuthCodeMail(String.valueOf(result),userId);
        PasswordAuthCodeEntity passwordAuthCodeEntity = new PasswordAuthCodeEntity();
        passwordAuthCodeEntity.setAuthCode(String.valueOf(result));
        passwordAuthCodeEntity.setFailCount(0);
        passwordAuthCodeEntity.setUserId(userId);
        passwordAuthCodeEntity.setIsEnabled("1");
        passwordAuthCodeRepository.save(passwordAuthCodeEntity);
        return true;
    }
//
//    public boolean sendNewPassword(String userId, String phoneNumber){
//        String newPassword = com.cardanonft.util.StringUtils.generateRandomPassword();
//        AligoSmsSendRequest aligoSmsSendRequest = new AligoSmsSendRequest();
//        aligoSmsSendRequest.setMsg("[BOM마켓] 새로운 비밀번호 "+ newPassword +"입니다.");
//        aligoSmsSendRequest.setSender(CommonConstants.KAKAO_SENDER_PHONE_NUMBER);
//        aligoSmsSendRequest.setReceiver(phoneNumber);
//        aligoSmsSendRequest.setMsg_type("");
//        aligoSmsSendRequest.setTitle("");
//        aligoSmsSendRequest.setDestination("");
//        Response<AligoSmsSendResponse> response = aligoSmsClient.sendSms(aligoSmsSendRequest);
//        if(response.isSuccessful()){
//            if("1".equals(response.body().getResultCode())) {
//                UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(userId, "1");
//                userEntity.setPassword(accountUtil.encodePassword(newPassword));
//                userRepository.save(userEntity);
//                return true;
//            }else{
//                logger.info("SMS 발송시 오류 발생 : 오류코드 "+response.body().getResultCode()+" : 오류메시지 "+response.body().getMessage() + " : 전화번호 " + phoneNumber);
//                return false;
//            }
//        }else{
//            return false;
//        }
//    }
//
    public PasswordAuthCodeEntity findAuthCode(String userId) throws Exception {
        PasswordAuthCodeEntity passwordAuthCodeEntity = passwordAuthCodeRepository.findTopByUserIdAndIsEnabledOrderByCreatedAtDesc(userId,"1");
        return passwordAuthCodeEntity;
    }
    public PasswordAuthCodeEntity findAuthCode(String userId, String authCode) throws Exception {
        PasswordAuthCodeEntity passwordAuthCodeEntity = passwordAuthCodeRepository.findTopByUserIdAndAuthCodeOrderByCreatedAtDesc(userId,authCode);
        return passwordAuthCodeEntity;
    }

    public void disableAuthCode(PasswordAuthCodeEntity passwordAuthCodeEntity) throws Exception {
        passwordAuthCodeEntity.setIsEnabled("0");
        passwordAuthCodeRepository.save(passwordAuthCodeEntity);
    }

    public void authCodeFailCountAdd(PasswordAuthCodeEntity passwordAuthCodeEntity) throws Exception {
        passwordAuthCodeEntity.setFailCount(passwordAuthCodeEntity.getFailCount()+1);
        passwordAuthCodeRepository.save(passwordAuthCodeEntity);
    }

    public boolean comparePassword(String userId, String password){
        UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(userId, "1");
        return accountUtil.confirmPasswordEcoded(password, userEntity.getPassword());
    }
//
//    public String sendPassword(String phone) throws Exception {
//        String resetPassword = generateTmpPassword();
//        AligoSmsSendRequest aligoSmsSendRequest = new AligoSmsSendRequest();
//        aligoSmsSendRequest.setMsg("초기화된 임시비밀번호 입니다. "+resetPassword);
//        aligoSmsSendRequest.setSender(CommonConstants.KAKAO_SENDER_PHONE_NUMBER);
//        aligoSmsSendRequest.setReceiver(phone);
//        aligoSmsSendRequest.setMsg_type("");
//        aligoSmsSendRequest.setTitle("");
//        aligoSmsSendRequest.setDestination("");
//        Response<AligoSmsSendResponse> response = aligoSmsClient.sendSms(aligoSmsSendRequest);
//        return resetPassword;
//    }

    public String generateTmpPassword () {
        String tempPassword = "";

        Integer result = new Random().nextInt(10000000) + 1000000;
        if (result > 10000000) {
            result = result - 1000000;
        }
        tempPassword = "a"+result;
        return tempPassword;
    }

    public void resetPassword(String userId, String password){
        UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(userId, "1");
        userEntity.setPassword(accountUtil.encodePassword(password));
        userRepository.save(userEntity);
    }

    public void withdraw(String userId){
        UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(userId, "1");
        UserRolesEntity userRolesEntity = userRolesRepository.findTopByUserIdAndIsEnabled(userId, "1");

        userEntity.setIsEnabled("0");
        userRolesEntity.setIsEnabled("0");

        userRepository.save(userEntity);
        userRolesRepository.save(userRolesEntity);
    }

    public UserRolesEntity findUserRole(String userId) {
        return userRolesRepository.findTopByUserIdAndIsEnabled(userId, "1");
    }

    public UserProfileVOResponse findUserProfile(String token) throws Exception {
        AuthToken authToken = authDao.getAuthToken(token);
        if(authToken == null) {
            throw new CustomBadCredentialException(RETURN_CODE.WRONG_CODE);
        }

        UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(authToken.getUser_id(), "1");

        return UserProfileVOResponse.builder()
                .nickname(userEntity.getNickName())
                .twitter(userEntity.getTwitter())
                .facebook(userEntity.getFacebook())
                .discord(userEntity.getDiscord())
                .build();

    }
    public boolean verifyTokenWithId (String token, String userId) throws Exception {
        AuthToken authToken = authDao.getAuthToken(token);
        if(authToken == null) {
            throw new CustomBadCredentialException(RETURN_CODE.INVALID_AUTHORITY);
        }
        UserEntity userEntity = userRepository.findTopByUserIdAndIsEnabled(authToken.getUser_id(), "1");
        if(userEntity == null || StringUtils.isEmpty(userEntity.getUserId()) || !userEntity.getUserId().equals(userId)){
            throw new CustomBadCredentialException(RETURN_CODE.INVALID_AUTHORITY);
        }
        return true;
    }
    public boolean verifyToken (String token) throws Exception {
        AuthToken authToken = authDao.getAuthToken(token);
        if(authToken == null) {
            throw new CustomBadCredentialException(RETURN_CODE.INVALID_AUTHORITY);
        }
        return true;
    }
}
