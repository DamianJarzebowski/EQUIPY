package pl.javastart.equipy.user;

import lombok.Data;
import pl.javastart.equipy.assigment.Assignment;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String pesel;
    @OneToMany(mappedBy = "user")
    private List<Assignment> assignments = new ArrayList<>();

}
