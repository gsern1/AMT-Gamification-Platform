package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Badge;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lux on 10.12.16.
 */
public interface BadgeRepository extends CrudRepository<Badge, Integer> {
}
