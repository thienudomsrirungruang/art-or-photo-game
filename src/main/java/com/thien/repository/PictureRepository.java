package com.thien.repository;

import com.thien.entity.PictureInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface PictureRepository extends CrudRepository<PictureInfo, Long> {

}
