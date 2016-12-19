package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="app_id")
    private Application application;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_badge", joinColumns = @JoinColumn(name = "badge_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    public Badge() {
    }

    public Badge(String name, Application application, Set<User> users) {
        this.name = name;
        this.application = application;
        this.users = users;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
