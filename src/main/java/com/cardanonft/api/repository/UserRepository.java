package com.cardanonft.api.repository;

import com.cardanonft.api.entity.UserEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findTopByUserId(String userId);
    UserEntity findTopByUserIdAndIsEnabled(String userId, String isEnabled);
//    UserEntity findTopByMobileAndIsEnabled(String mobile, String isEnabled);
    UserEntity findTopByEmailAndIsEnabled( String email, String isEnabled );
//    UserEntity findTopByEmail( String email);
//    UserEntity findTopByEmailAndIsEnabledAndSocialType(String email, String isEnabled, String socialType);
    UserEntity findTopByUserNameAndIsEnabled( String nickName, String isEnabled );
//    boolean existsUserEntityByUserIdAndMobileAndIsEnabled(String userId, String mobile, String isEnabled);
    boolean existsUserEntityByUserIdAndIsEnabled(String userId, String isEnabled);
//    UserEntity findTopByEmailAndIsEnabledOrderByCreatedAtDesc(String email, String isEnabled);
//    @Transactional
//    @Modifying
//    @Query(value=" UPDATE user "
//            + " SET mobile = :mobile , address_road = :addressRoad, address_detail = :addressDetail"
//            + ", postal_code = :postalCode, terms_marketing = :termsMarketing, terms_third = :termsThird "
//            + " WHERE user_id = :userId", nativeQuery = true)
//    void updateUserInfo(@Param("mobile") String mobile, @Param("addressRoad")String addressRoad
//            , @Param("addressDetail")String addressDetail, @Param("postalCode")String postalCode
//            , @Param("termsMarketing")String termsMarketing, @Param("termsThird")String termsThird
//            , @Param("userId")String userId
//    ) throws Exception;
    @Transactional
    @Modifying
    @Query(value=" UPDATE user "
            + " SET password = :password "
            + " WHERE user_id = :userId", nativeQuery = true)
    void updateUserPassword(@Param("password") String password
            , @Param("userId") String userId
    );
}
