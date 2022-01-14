package com.cardanonft.api.repository;

import com.cardanonft.api.entity.UserRolesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRolesRepository extends CrudRepository<UserRolesEntity, String> {
   UserRolesEntity findTopByUserIdAndIsEnabled(String userId,String isEnabled);
}
