package SmartEtuSport.repository;

import SmartEtuSport.entity.Activite;
import SmartEtuSport.entity.TypeSport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActiviteRepository extends JpaRepository<Activite, Long> {

    List<Activite> findByTypeSport(TypeSport typeSport);

    List<Activite> findByActifTrue();

    List<Activite> findByNomContainingIgnoreCase(String nom);
}
