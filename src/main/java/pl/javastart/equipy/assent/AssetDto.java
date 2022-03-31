package pl.javastart.equipy.assent;

import lombok.Data;
import pl.javastart.equipy.category.CategoryDto;

@Data
public class AssetDto {
    private final Long id;
    private final String name;
    private final String description;
    private final String serialNumber;
    private final CategoryDto category;
}
