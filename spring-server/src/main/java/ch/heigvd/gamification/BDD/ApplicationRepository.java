package ch.heigvd.gamification.BDD;

import ch.heigvd.gamification.BDD.Entities.Application;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lux on 10.12.16.
 */
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
}
