package pl.javastart.equipy.assent;

import pl.javastart.equipy.assigment.Assignment;

public class AssetAssignmentMapper {

    static AssetAssignmentDto toDto(Assignment assignment) {

        var dto = new AssetAssignmentDto();

        dto.setId(assignment.getId());
        dto.setStart(assignment.getStart());
        dto.setEnd(assignment.getEnd());

        var user = assignment.getUser();

        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setPesel(user.getPesel());

        return dto;
    }
}
