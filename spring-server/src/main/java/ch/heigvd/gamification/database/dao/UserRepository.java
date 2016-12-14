package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.PointScale;
import ch.heigvd.gamification.database.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lux on 10.12.16.
 */
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findByApplication(Application application);
    User findByIdAndApplication(long id, Application application);
    User findByName(String name);
}
