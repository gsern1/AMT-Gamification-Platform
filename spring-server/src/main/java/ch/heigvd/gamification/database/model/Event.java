package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    private User user;

    private long increase;

    @ManyToOne
    private PointScale pointScale;

    public Event() {
    }

    public Event(User user, int increase, PointScale pointScale) {
        this.user = user;
        this.increase = increase;
        this.pointScale = pointScale;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public long getIncrease() {
        return increase;
    }

    public void setIncrease(long increase) {
        this.increase = increase;
    }

    public PointScale getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScale pointScale) {
        this.pointScale = pointScale;
    }
}
