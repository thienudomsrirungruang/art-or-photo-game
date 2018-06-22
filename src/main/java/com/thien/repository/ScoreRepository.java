package com.thien.repository;

import com.thien.entity.ScoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ScoreRepository extends CrudRepository<ScoreInfo, Long> {
    List<ScoreInfo> findByGame_IdOrderByScoreDesc(int game_id);
}
