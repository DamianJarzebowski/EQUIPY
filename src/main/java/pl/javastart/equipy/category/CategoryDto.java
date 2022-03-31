package pl.javastart.equipy.category;

import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryDto {
    private final Long id;
    private final String name;
    private final String description;
}
