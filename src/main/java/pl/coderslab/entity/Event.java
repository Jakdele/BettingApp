package pl.coderslab.entity;


import lombok.Getter;
import lombok.Setter;
import pl.coderslab.entity.enums.EventType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private EventType type;

    private LocalDateTime created;

}
