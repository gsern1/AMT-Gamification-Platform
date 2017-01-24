package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by antoi on 12.12.2016.
 */
@Entity
@Table(name = "user_pointscale")
public class UserPointScale {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "pointscale_id")
    private PointScale pointScale;

    private long points;

    @Version
    private Integer version;

    public UserPointScale() {
    }

    public UserPointScale(User user, PointScale pointScale, long points) {
        this.user = user;
        this.pointScale = pointScale;
        this.points = points;
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

    public PointScale getPointScale() {
        return pointScale;
    }

    public void setPointScale(PointScale pointScale) {
        this.pointScale = pointScale;
    }

    public long getPoints() {
        return points;
    }

    public void setPoints(long points) {
        this.points = points;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
