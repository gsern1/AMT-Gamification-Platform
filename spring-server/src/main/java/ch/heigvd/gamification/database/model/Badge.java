package ch.heigvd.gamification.database.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Badge {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="app_id")
    private Application application;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_badge", joinColumns = @JoinColumn(name = "badge_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users;

    @Version
    private Integer version;

    public Badge() {
    }

    public Badge(String name, Application application, Set<User> users) {
        this.name = name;
        this.application = application;
        this.users = users;
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

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
