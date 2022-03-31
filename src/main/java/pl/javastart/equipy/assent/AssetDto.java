package pl.javastart.equipy.assent;

import lombok.Data;
import pl.javastart.equipy.category.CategoryDto;

@Data
public class AssetDto {

    private Long id;
    private String name;
    private String description;
    private String serialNumber;
    private String category;
}
