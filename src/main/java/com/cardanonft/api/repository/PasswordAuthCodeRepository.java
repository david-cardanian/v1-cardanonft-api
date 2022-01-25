package com.cardanonft.api.repository;

import com.cardanonft.api.entity.PasswordAuthCodeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PasswordAuthCodeRepository extends CrudRepository<PasswordAuthCodeEntity, Long> {
    PasswordAuthCodeEntity findTopByUserIdAndIsEnabledOrderByCreatedAtDesc(String userId,String isEnabled);

    PasswordAuthCodeEntity findTopByUserIdAndAuthCodeOrderByCreatedAtDesc(String userId,String authCode);
}
