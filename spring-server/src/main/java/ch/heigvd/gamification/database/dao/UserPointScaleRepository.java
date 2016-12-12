package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.User;
import ch.heigvd.gamification.database.model.UserPointScale;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lux on 10.12.16.
 */
public interface UserPointScaleRepository extends CrudRepository<UserPointScale, Integer> {
}
