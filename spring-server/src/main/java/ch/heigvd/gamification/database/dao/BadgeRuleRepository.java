package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.Badge;
import ch.heigvd.gamification.database.model.BadgeRule;
import ch.heigvd.gamification.database.model.PointScale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BadgeRuleRepository extends CrudRepository<BadgeRule, Long> {
    List<BadgeRule> findAll();
    List<BadgeRule> findByApplication(Application application);
    List<BadgeRule> findByPointscale(PointScale pointScale);
    BadgeRule findByType(String type);
}
