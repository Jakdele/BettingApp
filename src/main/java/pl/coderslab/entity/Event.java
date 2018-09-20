package pl.coderslab.entity;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.entity.enums.EventType;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private EventType type;

    @Column(columnDefinition = "Datetime")
    private LocalDateTime created;

    private String teamName;


    public Event() {
    }

    public Event(EventType type, LocalDateTime created) {
        this.type = type;
        this.created = created;
    }
}
