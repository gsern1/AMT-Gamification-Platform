package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.PointScaleRule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lux on 24.01.17.
 */
public interface PointScaleRuleRepository extends CrudRepository<PointScaleRule, Long> {
    PointScaleRule findByType(String type);
    List<PointScaleRule> findByApplication(Application application);

}
