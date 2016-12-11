package ch.heigvd.gamification.database.model;

import javax.persistence.*;

/**
 * Created by antoi on 11.12.2016.
 */
@Entity
public class Event {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String name;

    @ManyToOne
    private User user;

    private int increase;

    @ManyToOne
    private PointScale pointScale;
}
