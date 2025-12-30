package SmartEtuSport.repository;

import SmartEtuSport.entity.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClasseRepository extends JpaRepository<Class, Long> {

    List<Class> findByNiveauAcademiqueId(Long niveauAcademiqueId);

    List<Class> findByAnneeScolaire(String anneeScolaire);

    List<Class> findByProfesseurId(Long professeurId);

    List<Class> findByNiveauAcademiqueIdAndAnneeScolaire(Long niveauAcademiqueId, String anneeScolaire);
}
