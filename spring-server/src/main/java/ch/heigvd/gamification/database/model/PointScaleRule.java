package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class PointScaleRule extends Rule{

    @ManyToOne
    private PointScale pointscale;

    private long increment;

    public PointScaleRule() {
        super();
    }

    public PointScaleRule(Application application, String type, PointScale pointscale, long increment) {
        super(application, type);
        this.pointscale = pointscale;
        this.increment = increment;
    }

    public PointScale getPointscale() {
        return pointscale;
    }

    public void setPointscale(PointScale pointscale) {
        this.pointscale = pointscale;
    }

    public long getIncrement() {
        return increment;
    }

    public void setIncrement(long increment) {
        this.increment = increment;
    }
}