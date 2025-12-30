package SmartEtuSport.repository;

import SmartEtuSport.entity.Note;
import SmartEtuSport.entity.TypeEvaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByEtudiantId(Long etudiantId);

    List<Note> findByActiviteId(Long activiteId);

    List<Note> findBySessionId(Long sessionId);

    List<Note> findByEtudiantIdAndActiviteId(Long etudiantId, Long activiteId);

    List<Note> findByTypeEvaluation(TypeEvaluation typeEvaluation);

    @Query("SELECT AVG(n.valeur) FROM Note n WHERE n.etudiant.id = :etudiantId")
    Double calculerMoyenneGenerale(@Param("etudiantId") Long etudiantId);

    @Query("SELECT AVG(n.valeur) FROM Note n WHERE n.etudiant.id = :etudiantId AND n.activite.id = :activiteId")
    Double calculerMoyenneParActivite(@Param("etudiantId") Long etudiantId,
                                      @Param("activiteId") Long activiteId);

    @Query("SELECT SUM(n.valeur * n.coefficient) / SUM(n.coefficient) " +
            "FROM Note n WHERE n.etudiant.id = :etudiantId")
    Double calculerMoyennePonderee(@Param("etudiantId") Long etudiantId);

    @Query("SELECT n FROM Note n WHERE n.etudiant.classe.id = :classeId")
    List<Note> findByClasseId(@Param("classeId") Long classeId);
}
