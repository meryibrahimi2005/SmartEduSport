package SmartEtuSport.repository;

import SmartEtuSport.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    List<Etudiant> findByClasseId(Long classeId);

    Optional<Etudiant> findByNumeroEtudiant(String numeroEtudiant);

    Optional<Etudiant> findByEmail(String email);

    List<Etudiant> findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(String nom, String prenom);

    @Query("SELECT e FROM Etudiant e WHERE e.classe.id = :classeId AND e.actif = true")
    List<Etudiant> findActiveByClasseId(@Param("classeId") Long classeId);

    Long countByClasseId(Long classeId);
}