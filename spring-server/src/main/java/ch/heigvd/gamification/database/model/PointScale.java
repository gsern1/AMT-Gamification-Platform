package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class PointScale {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne
    private Application application;

    @OneToMany
    private Set<PointScaleRule> pointScaleRule;

    public PointScale() {
    }

    public PointScale(String name, Application application) {
        this.name = name;
        this.application = application;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Set<PointScaleRule> getPointScaleRule() {
        return pointScaleRule;
    }

    public void setPointScaleRule(Set<PointScaleRule> pointScaleRule) {
        this.pointScaleRule = pointScaleRule;
    }
}
