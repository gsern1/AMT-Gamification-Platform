DROP SCHEMA IF EXISTS gamification;
CREATE SCHEMA gamification;

USE gamification;

CREATE TABLE application(
	id INT NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL UNIQUE,
    password TEXT NOT NULL,
    CONSTRAINT application_PK PRIMARY KEY (id)
);

CREATE TABLE badge(
	id INT NOT NULL AUTO_INCREMENT,
    name varchar(100) NOT NULL UNIQUE,
    app_id INT NOT NULL,
    CONSTRAINT application_PK PRIMARY KEY (id),
    CONSTRAINT badge_application_FK FOREIGN KEY (app_id) REFERENCES application(id)
);

INSERT INTO application (name, password) VALUES ("test", "test");
SELECT * FROM application;
SELECT * FROM badge;