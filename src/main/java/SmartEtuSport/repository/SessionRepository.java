package SmartEtuSport.repository;

import SmartEtuSport.entity.Session;
import SmartEtuSport.entity.TypeSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SessionRepository extends JpaRepository<Session, Long> {

    List<Session> findByClasseId(Long classeId);

    List<Session> findBySemestreId(Long semestreId);

    List<Session> findByProfesseurId(Long professeurId);

    List<Session> findByActiviteId(Long activiteId);

    List<Session> findByType(TypeSession type);

    @Query("SELECT s FROM Session s WHERE s.classe.id = :classeId AND s.dateHeure BETWEEN :debut AND :fin")
    List<Session> findByClasseIdAndDateBetween(@Param("classeId") Long classeId,
                                               @Param("debut") LocalDateTime debut,
                                               @Param("fin") LocalDateTime fin);

    @Query("SELECT s FROM Session s WHERE s.professeur.id = :professeurId AND s.dateHeure >= :date ORDER BY s.dateHeure ASC")
    List<Session> findUpcomingSessionsByProfesseur(@Param("professeurId") Long professeurId,
                                                   @Param("date") LocalDateTime date);
}
