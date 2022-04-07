package pl.javastart.equipy.assigment;

import lombok.Data;
import pl.javastart.equipy.assent.Asset;
import pl.javastart.equipy.user.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
public class Assigment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "asser_id")
    private Asset asset;
}
