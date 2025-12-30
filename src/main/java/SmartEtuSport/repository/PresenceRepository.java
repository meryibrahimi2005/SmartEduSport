package SmartEtuSport.repository;

import SmartEtuSport.entity.Presence;
import SmartEtuSport.entity.StatutPresence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PresenceRepository extends JpaRepository<Presence, Long> {

    List<Presence> findByEtudiantId(Long etudiantId);

    List<Presence> findBySessionId(Long sessionId);

    Optional<Presence> findByEtudiantIdAndSessionId(Long etudiantId, Long sessionId);

    List<Presence> findByStatut(StatutPresence statut);

    @Query("SELECT COUNT(p) FROM Presence p WHERE p.etudiant.id = :etudiantId AND p.statut = :statut")
    Long countByEtudiantIdAndStatut(@Param("etudiantId") Long etudiantId,
                                    @Param("statut") StatutPresence statut);

    @Query("SELECT p FROM Presence p WHERE p.etudiant.classe.id = :classeId AND p.session.id = :sessionId")
    List<Presence> findByClasseIdAndSessionId(@Param("classeId") Long classeId,
                                              @Param("sessionId") Long sessionId);
}