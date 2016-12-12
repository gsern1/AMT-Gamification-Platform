package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import ch.heigvd.gamification.database.model.PointScale;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by lux on 10.12.16.
 */
public interface PointScaleRepository extends CrudRepository<PointScale, Integer> {
    List<PointScale> findByApplication(Application application);
    PointScale findByIdAndApplication(long id, Application application);
}
