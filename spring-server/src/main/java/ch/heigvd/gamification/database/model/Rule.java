package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by lux on 24.01.17.
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Rule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Application application;

    private String type;

    public Rule(Application application, String type) {
        this.application = application;
        this.type = type;
    }

    public Rule() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
