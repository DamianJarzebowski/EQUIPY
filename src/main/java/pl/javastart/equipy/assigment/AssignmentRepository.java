package pl.javastart.equipy.assigment;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AssignmentRepository extends JpaRepository<Assignment, Long> {
        Optional<Assignment> findByAsset_IdAndEndIsNull(Long assetId);
    }

