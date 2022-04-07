package pl.javastart.equipy.assigment;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AssignmentDto {

    private Long id;
    private LocalDateTime start;
    private LocalDateTime end;
    private Long assetId;
    private Long userId;
    private String assetName;
    private String assetSerialNumber;
}
