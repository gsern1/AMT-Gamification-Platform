package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class BadgeRule {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String type;

    @ManyToOne
    private Application application;

    @ManyToOne
    private Application badge;

    @ManyToOne
    private Application pointscale;

    private long threshold;

    public BadgeRule() {
    }

    public BadgeRule(String type, Application application, Application badge, Application pointscale, long threshold) {
        this.type = type;
        this.application = application;
        this.badge = badge;
        this.pointscale = pointscale;
        this.threshold = threshold;
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

    public Application getBadge() {
        return badge;
    }

    public void setBadge(Application badge) {
        this.badge = badge;
    }

    public Application getPointscale() {
        return pointscale;
    }

    public void setPointscale(Application pointscale) {
        this.pointscale = pointscale;
    }

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }
}