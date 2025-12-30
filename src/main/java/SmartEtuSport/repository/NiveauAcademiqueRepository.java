package SmartEtuSport.repository;

import SmartEtuSport.entity.NiveauAcademique;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface NiveauAcademiqueRepository extends JpaRepository<NiveauAcademique, Long> {

    Optional<NiveauAcademique> findByNom(String nom);

    List<NiveauAcademique> findAllByOrderByOrdreAffichageAsc();
}