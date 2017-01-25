package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.database.model.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lux on 10.12.16.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByApplication(Application application);
    User findByIdAndApplication(long id, Application application);
    User findByUsernameAndApplication(String username, Application application);
    User findByUsername(String username);

    @Query(value = "select u.username as username, count(b.badge_id) as numberOfBadges from user u " +
            "inner join user_badge b " +
            "inner join application a " +
            "where a.id = ?1 " +
            "group by u.id", nativeQuery = true)
    List<Object[]> findByNumberOfBadges(int application);
}
