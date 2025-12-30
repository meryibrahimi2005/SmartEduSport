package SmartEtuSport.service;

import SmartEtuSport.entity.Professeur;
import SmartEtuSport.repository.ProfesseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final ProfesseurRepository professeurRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private Professeur currentUser;

    public boolean login(String email, String password) {
        var professeur = professeurRepository.findByEmail(email);

        if (professeur.isPresent()) {
            Professeur prof = professeur.get();
            // Pour le développement, accepter le mot de passe en clair
            // TODO: Utiliser passwordEncoder.matches(password, prof.getMotDePasse())
            if (password.equals(prof.getMotDePasse()) ||
                    passwordEncoder.matches(password, prof.getMotDePasse())) {
                this.currentUser = prof;
                return true;
            }
        }
        return false;
    }

    public Professeur getCurrentUser() {
        return currentUser;
    }

    public void logout() {
        this.currentUser = null;
    }

    public boolean register(Professeur professeur) {
        try {
            professeur.setMotDePasse(passwordEncoder.encode(professeur.getMotDePasse()));
            professeurRepository.save(professeur);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}