package com.thien.repository;

import com.thien.entity.ScoreInfo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<ScoreInfo, Long> {
    List<ScoreInfo> findByGameIdOrderByScoreDesc(int gameId);
    List<ScoreInfo> findByUserId(int userId);
    List<ScoreInfo> findByUserIdAndGameId(int userId, int gameId);
    List<ScoreInfo> findByUserIdAndGameIdOrderByScoreDesc(int userId, int gameId);
    List<ScoreInfo> findByUserIdAndGameIdOrderByScoreDateDescIdDesc(int userId, int gameId);

    @Query("select score from ScoreInfo score where score.scoreDate > ?1 and score.gameId = ?2" +
            " order by score.score desc, score.scoreDate desc, id desc")
    List<ScoreInfo> findWeeklyTopScoreFromDate(Date date, int gameId);
}
