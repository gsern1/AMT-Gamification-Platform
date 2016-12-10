package ch.heigvd.gamification.database;

import ch.heigvd.gamification.database.model.Application;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lux on 10.12.16.
 */
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
