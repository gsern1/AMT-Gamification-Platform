package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    @Column(unique=true)
    private String username;

    @ManyToOne
    private Application application;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_badge", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "badge_id", referencedColumnName = "id"))
    private Set<Badge> badges;

    @Version
    private Integer version;

    public User() {
    }

    public User(String name, Application application) {
        this.username = name;
        this.application = application;
    }

    public User(String name, Application application, Set<Badge> badges) {
        this.username = name;
        this.application = application;
        this.badges = badges;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Application getApplication() {
        return application;
    }

    public void setApplication(Application application) {
        this.application = application;
    }

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
