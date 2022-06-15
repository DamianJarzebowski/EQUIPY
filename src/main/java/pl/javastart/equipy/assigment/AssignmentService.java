package pl.javastart.equipy.assigment;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.javastart.equipy.assent.Asset;
import pl.javastart.equipy.assent.AssetRepository;
import pl.javastart.equipy.user.User;
import pl.javastart.equipy.user.UserRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final AssetRepository assetRepository;
    private final UserRepository userRepository;

    AssignmentDto createAssignment(AssignmentDto assignmentDto) {
        Optional<Assignment> activeAssignmentForAsset = assignmentRepository
                .findByAsset_IdAndEndIsNull(assignmentDto.getAssetId());
        activeAssignmentForAsset.ifPresent((a) -> {
            throw new InvalidAssignmentException("To wyposażenie jest aktualnie do kogoś przypisane");
        });
        Optional<User> user = userRepository.findById(assignmentDto.getUserId());
        Optional<Asset> asset = assetRepository.findById(assignmentDto.getAssetId());
        Assignment assignment = new Assignment();
        Long userId = assignmentDto.getUserId();
        Long assetId = assignmentDto.getAssetId();
        assignment.setUser(user.orElseThrow(() ->
                new InvalidAssignmentException("Brak użytkownika z id: " + userId)));
        assignment.setAsset(asset.orElseThrow(() ->
                new InvalidAssignmentException("Brak wyposażenia z id: " + assetId)));
        assignment.setStart(LocalDateTime.now());
        return AssignmentMapper.toDto(assignmentRepository.save(assignment));
    }

    @Transactional
    public LocalDateTime finishAssignment(Long assignmentId) {
        Optional<Assignment> assignment = assignmentRepository.findById(assignmentId);
        Assignment assignmentEntity = assignment.orElseThrow(AssignmentNotFoundException::new);
        if (assignmentEntity.getEnd() != null)
            throw new AssignmentAlreadyFinishedException();
        else
            assignmentEntity.setEnd(LocalDateTime.now());
        return assignmentEntity.getEnd();
    }
}
