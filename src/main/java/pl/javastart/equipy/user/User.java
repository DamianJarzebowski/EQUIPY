package pl.javastart.equipy.user;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String pesel;

}
