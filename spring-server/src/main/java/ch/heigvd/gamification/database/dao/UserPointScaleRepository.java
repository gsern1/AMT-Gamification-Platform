package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.User;
import ch.heigvd.gamification.database.model.UserPointScale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lux on 10.12.16.
 */
public interface UserPointScaleRepository extends CrudRepository<UserPointScale, Integer> {
    List<UserPointScale> findByUser(User user);
}
