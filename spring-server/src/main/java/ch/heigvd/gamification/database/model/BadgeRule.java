package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.awt.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class BadgeRule extends Rule {

    @ManyToOne
    private Badge badge;

    @ManyToOne
    private PointScale pointscale;

    private long threshold;

    public BadgeRule() {
        super();
    }

    public BadgeRule(Application application, String type, Badge badge, PointScale pointscale, long threshold) {
        super(application, type);
        this.badge = badge;
        this.pointscale = pointscale;
        this.threshold = threshold;
    }

    public Badge getBadge() {
        return badge;
    }

    public void setBadge(Badge badge) {
        this.badge = badge;
    }

    public PointScale getPointscale() {
        return pointscale;
    }

    public void setPointscale(PointScale pointscale) {
        this.pointscale = pointscale;
    }

    public long getThreshold() {
        return threshold;
    }

    public void setThreshold(long threshold) {
        this.threshold = threshold;
    }
}