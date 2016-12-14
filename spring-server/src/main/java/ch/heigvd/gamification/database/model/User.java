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
    private String name;

    @ManyToOne
    private Application application;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_badge", joinColumns = @JoinColumn(name = "badge_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<Badge> badges;

    public User() {
    }

    public User(String name, Application application) {
        this.name = name;
        this.application = application;
    }

    public User(String name, Application application, Set<Badge> badges) {
        this.name = name;
        this.application = application;
        this.badges = badges;
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

    public Set<Badge> getBadges() {
        return badges;
    }

    public void setBadges(Set<Badge> badges) {
        this.badges = badges;
    }
}
