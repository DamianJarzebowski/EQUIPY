package pl.javastart.equipy.assent;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AssetService {

    private final AssetRepository assetRepository;
    private final AssetMapper assetMapper;

    List<AssetDto> findAll() {
        return assetRepository.findAll()
                .stream()
                .map(assetMapper::toDto)
                .toList();
    }

    List<AssetDto> findAllByNameOrSerialNumber(String text) {
        return assetRepository.findAllByNameOrSerialNumber(text)
                .stream()
                .map(assetMapper::toDto)
                .toList();
    }

    AssetDto save(AssetDto assetDto) {
        Optional<Asset> assetBySerialNo = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        assetBySerialNo.ifPresent(a -> {
            throw new DuplicateSerialNumberException();
        });
        return mapAndSave(assetDto);
    }

    AssetDto update(AssetDto assetDto) {
        Optional<Asset> assetBySerialNo = assetRepository.findBySerialNumber(assetDto.getSerialNumber());
        assetBySerialNo.ifPresent(a -> {
            if(!a.getId().equals(assetDto.getId()))
                throw new DuplicateSerialNumberException();
        });
        return mapAndSave(assetDto);
    }

    private AssetDto mapAndSave(AssetDto assetDto) {
        Asset assetEntity = assetMapper.toEntity(assetDto);
        Asset savedAsset = assetRepository.save(assetEntity);
        return assetMapper.toDto(savedAsset);
    }

    Optional<AssetDto> findById(Long id) {
        return assetRepository.findById(id).map(assetMapper::toDto);
    }

    List<AssetAssignmentDto> getAssetAssignments(Long id) {
        return assetRepository.findById(id)
                .map(Asset::getAssignments)
                .orElseThrow(AssetNotFoundException::new)
                .stream()
                .map(AssetAssignmentMapper::toDto)
                .toList();
    }


}
