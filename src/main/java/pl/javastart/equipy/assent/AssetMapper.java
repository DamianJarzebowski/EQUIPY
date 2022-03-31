package pl.javastart.equipy.assent;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.category.CategoryRepository;

@Service
public class AssetMapper {

    private CategoryRepository categoryRepository;

    public AssetMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    AssetDto toDto(Asset asset) {
        AssetDto dto = new AssetDto();

        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setDescription(asset.getDescription());
        dto.setSerialNumber(asset.getSerialNumber());
        if (asset.getCategory() != null)
            dto.setCategory(asset.getCategory().getName());
        return dto;
    }
}
