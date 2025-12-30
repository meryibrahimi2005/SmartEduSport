package SmartEtuSport.repository;

import SmartEtuSport.entity.Semestre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface SemestreRepository extends JpaRepository<Semestre, Long> {

    List<Semestre> findByAnneeScolaire(String anneeScolaire);

    Optional<Semestre> findByNumeroAndAnneeScolaire(Integer numero, String anneeScolaire);

    @Query("SELECT s FROM Semestre s WHERE :date BETWEEN s.dateDebut AND s.dateFin AND s.actif = true")
    Optional<Semestre> findSemestreActif(LocalDate date);

    List<Semestre> findByActifTrueOrderByAnneeScolaireDescNumeroAsc();
}
