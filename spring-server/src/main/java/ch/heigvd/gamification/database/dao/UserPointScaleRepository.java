package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.database.model.User;
import ch.heigvd.gamification.database.model.UserPointScale;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserPointScaleRepository extends CrudRepository<UserPointScale, Integer> {
    List<UserPointScale> findByUser(User user);

    List<UserPointScale> findByUserAndPointScale(User user, PointScale pointScale);

    @Query(value = "select p.id as pointscale_id, p.name as pointscale_name, sum(up.points) as points from user_pointscale up " +
            "inner join point_scale p " +
            "on p.id = up.pointscale_id " +
            "where p.id = ?1 " +
            "group by p.id ", nativeQuery = true)
    List<Object[]> findSumPointScalePerUser(long userId);
}
