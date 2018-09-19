package pl.coderslab.entity;


import lombok.Data;
import pl.coderslab.entity.enums.EventType;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Data
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private EventType type;

    private LocalDateTime created;

}
