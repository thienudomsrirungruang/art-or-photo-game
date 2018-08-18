package com.thien.repository;

import com.thien.entity.UserInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface UserRepository extends CrudRepository<UserInfo, Long> {
    List<UserInfo> findByUsername(String username);
    List<UserInfo> findById(int id);
}
