package SmartEtuSport.repository;

import SmartEtuSport.entity.Professeur;
import SmartEtuSport.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesseurRepository extends JpaRepository<Professeur, Long> {

    Optional<Professeur> findByEmail(String email);

    List<Professeur> findByRole(Role role);

    List<Professeur> findByActifTrue();

    List<Professeur> findBySpecialite(String specialite);
}
