package com.cardanonft.api.dao;

import com.cardanonft.api.vo.account.User;
import com.cardanonft.api.vo.account.Users;
import com.cardanonft.api.vo.auth.AccessControlRule;
import com.cardanonft.api.vo.auth.AuthToken;
import com.cardanonft.api.vo.auth.Authority2;
import com.cardanonft.api.vo.auth.RSAEncryptionKey;
import com.cardanonft.api.vo.log.ActionLog;
import com.cardanonft.api.vo.log.AppLog;
import com.cardanonft.api.vo.message.MessageQueue;
import com.cardanonft.api.vo.message.PostingReads;
import com.cardanonft.api.vo.message.Postings;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
public class AuthDao {
    @Autowired
    SqlSession sqlSession;

    ///
    public int getUserIdByAccount(String account) throws Exception {
        List<Integer> cadidates = sqlSession.selectList("auth.getUserIdByAccount", account);
        if (cadidates.size() > 0) {
            return cadidates.get(0);
        } else {
            return 0;
        }
    }

    /// 비밀번호 암호값 가져오기
    public Users getPassword(String username) throws Exception {
        return sqlSession.selectOne("auth.getPassword", username);
    }

    public void updatePasswordFailCountAndIsLocked(Users users) throws Exception {
        sqlSession.update("auth.updatePasswordFailCountAndIsLocked", users);
    }

    public void resetPasswordFailCountAndIsLocked(String username) throws Exception {
        sqlSession.update("auth.resetPasswordFailCountAndIsLocked", username);
    }

    /// 비밀번호 체크 쿼리
    public int getCountPasswordEqual(CountPasswordEqualParams params) throws Exception {
        return sqlSession.selectOne("auth.getCountPasswordEqual", params);
    }

    /// 로그인 한 사용자의 웹 모듈 별 접속 권한
    public List<Authority2> getAuthorities(String userId) throws Exception {
        return sqlSession.selectList("auth.getAuthorities", userId);
    }

    /// 엑세스 컨트롤 규칙 정의 조회
    public List<AccessControlRule> getAccessControlRules(AccessControlRulesParams params) throws Exception {
        return sqlSession.selectList("auth.getAccessControlRules", params);
    }

    /// 최근 로그인 앱 로그 가져오기
    public AppLog getLastestAppLog(int user_id) throws Exception {
        return sqlSession.selectOne("auth.getLastestAppLog", user_id);
    }

    /// 사용자 특이 로그인 시, 관련 매장 조회
    public List<Integer> getStores(int user_id) throws Exception {
        return sqlSession.selectList("auth.getStores", user_id);
    }

    /// 사용자 특이 로그인 시, 관련 관리자 조회
    public List<User> getManagerUsers(int store_id) throws Exception {
        return sqlSession.selectList("auth.getManagerUsers", store_id);
    }

    /// 앱 로그 삽입
    public void insertAppLog(AppLog params) throws Exception {
        sqlSession.insert("auth.insertAppLog", params);
    }

    /// 앱 로그 삽입
    public void insertActionLog(ActionLog params) throws Exception {
        sqlSession.insert("auth.insertActionLog", params);
    }

    /// 인증 토큰 삽입
    public void insertAuthToken(AuthToken params) throws Exception {
        sqlSession.insert("auth.insertAuthToken", params);
    }

    /// 인증 토큰 업데이트
    public void updateAuthToken(AuthToken params) throws Exception {
        sqlSession.insert("auth.updateAuthToken", params);
    }

    /// 인증 토큰 연장을 위한 기존 토큰 비활성화
    public void disableAuthToken(AuthToken params) throws Exception {
        sqlSession.insert("auth.disableAuthToken", params);
    }

    /// 인증 토큰 조회
    public AuthToken getAuthToken(String token) throws Exception {
        return sqlSession.selectOne("auth.getAuthToken", token);
    }

    /// 인증 시 메일/푸시 발송을 위한 메세지큐 삽입
    public void insertMessageQueue(MessageQueue params) throws Exception {
        sqlSession.insert("auth.insertMessageQueue", params);
    }

    public void insertSendPush(MessageQueue messageQueue) throws Exception{
        sqlSession.insert("auth.insertSendPush", messageQueue);
    }

    public String getUsername(long user_id) throws Exception{
        return sqlSession.selectOne("auth.getUserName" , user_id );
    }

    public int getNonFreeCnt(Map<String,Object> dataMap) throws Exception {
        return sqlSession.selectOne("auth.getNonFreeCnt", dataMap);
    }

    public Map<String, Object> getUserIdByGoogleIdToken(String idToken) {
        return sqlSession.selectOne("auth.getUserIdByGoogleIdToken", idToken);
    }

    public void updateGoogleUid(Map<String, Object> dataMap) {
        sqlSession.update("auth.updateGoogleUid", dataMap);
    }

    public Map<String, Object> getTaxAccountantInviteCode(String inviteCode) throws Exception{
        return sqlSession.selectOne("auth.getTaxAccountantInviteCode" , inviteCode );
    }

    public void updateAuthTokenIsEnabled(AuthToken params) throws Exception {
        sqlSession.update("auth.updateAuthTokenIsEnabled", params);
    }
    public Map<String, Object> getToken(Map<String, Object> params) throws Exception{
        return sqlSession.selectOne("auth.getToken" , params);
    }

    @Transactional
    public void insertRSAEncryptionKey(RSAEncryptionKey rsaEncryptionKey) throws Exception {
        sqlSession.insert("auth.insertRSAEncryptionKey", rsaEncryptionKey);
    }
}
