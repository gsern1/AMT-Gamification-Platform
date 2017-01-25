package ch.heigvd.gamification.database.dao;

import ch.heigvd.gamification.database.model.Application;
import org.springframework.data.repository.CrudRepository;

public interface ApplicationRepository extends CrudRepository<Application, Integer> {
    Application findByName(String name);
}
