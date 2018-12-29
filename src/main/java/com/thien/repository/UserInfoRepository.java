package com.thien.repository;

import com.thien.entity.UserRoleInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserInfoRepository extends CrudRepository<UserRoleInfo, Long> {

}
