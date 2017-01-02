package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class PointScaleRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    @ManyToOne
    private Application application;

    @ManyToOne
    private Application pointscale;

    private long increment;

    public PointScaleRule() {
    }

    public PointScaleRule(String type, Application application, Application pointscale, long increment) {
        this.type = type;
        this.application = application;
        this.pointscale = pointscale;
        this.increment = increment;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Application getPointscale() {
        return pointscale;
    }

    public void setPointscale(Application pointscale) {
        this.pointscale = pointscale;
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }
}