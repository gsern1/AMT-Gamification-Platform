package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class BadgeRule extends Rule {

    @ManyToOne
    private Application badge;

    @ManyToOne
    private Application pointscale;

    private long threshold;

    public BadgeRule() {
        super();
    }

    public BadgeRule(Application application, String type, Application badge, Application pointscale, long threshold) {
        super(application, type);
        this.badge = badge;
        this.pointscale = pointscale;
        this.threshold = threshold;
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