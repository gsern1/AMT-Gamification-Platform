package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.PointScale;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lux on 10.12.16.
 */
public interface PointScaleRepository extends CrudRepository<PointScale, Integer> {
}
