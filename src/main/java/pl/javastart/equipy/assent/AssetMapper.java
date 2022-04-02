package pl.javastart.equipy.assent;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.category.Category;
import pl.javastart.equipy.category.CategoryRepository;

import java.util.Optional;

@Service
public class AssetMapper {

    private CategoryRepository categoryRepository;

    public AssetMapper(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    AssetDto toDto(Asset asset) {
        var dto = new AssetDto();

        dto.setId(asset.getId());
        dto.setName(asset.getName());
        dto.setDescription(asset.getDescription());
        dto.setSerialNumber(asset.getSerialNumber());
        if (asset.getCategory() != null)
            dto.setCategory(asset.getCategory().getName());
        return dto;
    }

    // Dlaczego nie moge pobrac kategori z dto tylko ustawiam ja jakos na około Przekształcenie obiektu Asset na AssetDto sprowadza się jedynie do przepisania odpowiednich pól z jednego obiektu do drugiego. W przypadku przekształcania Dto na Asset musimy dodatkowo skorzystać z CategoryRepository w celu pobrania obiektu Category po nazwie. Jeżeli obiekt Dto zawiera nazwę kategorii, której nie ma w bazie pole to pozostanie nieustawione (null) w obiekcie encji.
    Asset toEntity(AssetDto assetDto) {
        var entity = new Asset();

        entity.setId(assetDto.getId());
        entity.setName(assetDto.getName());
        entity.setDescription(assetDto.getDescription());
        Optional<Category> category = categoryRepository.findByName(assetDto.getCategory());
        category.ifPresent(entity::setCategory);
        entity.setSerialNumber(assetDto.getSerialNumber());
        return entity;
    }

}
