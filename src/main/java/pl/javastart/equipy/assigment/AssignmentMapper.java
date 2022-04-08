package pl.javastart.equipy.assigment;

import pl.javastart.equipy.assent.Asset;

public class AssignmentMapper {

        public static AssignmentDto toDto(Assignment assignment){

            var dto = new AssignmentDto();
            var user = assignment.getUser();

            dto.setId(assignment.getId());
            dto.setStart(assignment.getStart());
            dto.setEnd(assignment.getEnd());
            dto.setUserId(user.getId());
            Asset asset = assignment.getAsset();
            dto.setAssetId(asset.getId());
            return dto;
        }

    }

