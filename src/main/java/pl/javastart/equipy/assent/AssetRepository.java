package pl.javastart.equipy.assent;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset, Long> {

    //jak to query działa
    @Query("select a from Asset a where lower(a.name) like lower(concat('%', :search, '%')) " +
            "or lower(a.serialNumber) like lower(concat('%', :search, '%'))")
    List<Asset> findAllByNameOrSerialNumber(@Param("search") String search);

    Optional<Asset> findBySerialNumber(String serialNumber);

}
