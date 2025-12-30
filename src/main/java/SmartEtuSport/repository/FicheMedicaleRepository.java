package SmartEtuSport.repository;

import SmartEtuSport.entity.FicheMedicale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FicheMedicaleRepository extends JpaRepository<FicheMedicale, Long> {

    Optional<FicheMedicale> findByEtudiantId(Long etudiantId);
}