package pl.javastart.equipy.assent;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssetAssignmentDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long userId;
    private String firstName;
    private String lastName;
    private String pesel;

}
