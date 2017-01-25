package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.util.Set;

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

    @Version
    private Integer version;

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

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
