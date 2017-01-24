package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.Badge;
import ch.heigvd.gamification.database.model.BadgeRule;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lux on 10.12.16.
 */
public interface BadgeRuleRepository extends CrudRepository<BadgeRule, Integer> {
    List<BadgeRule> findAll();
    List<BadgeRule> findByApplication(Application application);
}
