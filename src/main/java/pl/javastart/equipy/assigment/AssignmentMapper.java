package pl.javastart.equipy.assigment;

import pl.javastart.equipy.assent.Asset;

public class AssignmentMapper {

    public static AssignmentDto toDto(Assignment assignment) {

        var dto = new AssignmentDto();

        dto.setId(assignment.getId());
        dto.setAssetId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());

        Asset asset = assignment.getAsset();

        dto.setAssetName(asset.getName());
        dto.setAssetSerialNumber(asset.getSerialNumber());
        return dto;
    }

}
