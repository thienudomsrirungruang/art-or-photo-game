package com.thien.repository;

import com.thien.entity.ScoreInfo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface ScoreRepository extends CrudRepository<ScoreInfo, Long> {
    List<ScoreInfo> findByGameIdOrderByScoreDesc(int gameId);
    List<ScoreInfo> findByUserId(int userId);
    List<ScoreInfo> findByUserIdAndGameId(int userId, int gameId);
    List<ScoreInfo> findByUserIdAndGameIdOrderByScoreDesc(int userId, int gameId);
    List<ScoreInfo> findByUserIdAndGameIdOrderByScoreDateDescIdDesc(int userId, int gameId);
}
