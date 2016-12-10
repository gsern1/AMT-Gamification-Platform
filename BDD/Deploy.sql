DROP SCHEMA IF EXISTS gamification;
CREATE SCHEMA gamification;

USE gamification;

CREATE TABLE Application(
	id INT NOT NULL AUTO_INCREMENT,
    name TEXT NOT NULL,
    password TEXT NOT NULL,
    CONSTRAINT Application_FK PRIMARY KEY (id)
);

INSERT INTO Application (name, password) VALUES ("test", "test");
SELECT * FROM Application;