package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by lux on 10.12.16.
 */
public interface ApplicationRepository extends CrudRepository<Application, Integer> {
    Application findByName(String name);
    void deleteByName(String name);
}