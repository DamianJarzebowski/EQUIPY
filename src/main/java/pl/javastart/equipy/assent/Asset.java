package pl.javastart.equipy.assent;


import lombok.Data;
import pl.javastart.equipy.assigment.Assignment;
import pl.javastart.equipy.category.Category;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Column(unique = true)
    private String serialNumber;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @OneToMany(mappedBy = "asset")
    private List<Assignment> assignments = new ArrayList<>();

}
