package SmartEtuSport.service;

import SmartEtuSport.dto.EtudiantDTO;
import SmartEtuSport.entity.Etudiant;
import SmartEtuSport.entity.Class;
import SmartEtuSport.repository.EtudiantRepository;
import SmartEtuSport.repository.ClasseRepository;
import SmartEtuSport.repository.NoteRepository;
import SmartEtuSport.repository.PresenceRepository;
import SmartEtuSport.entity.StatutPresence;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EtudiantService {

    private final EtudiantRepository etudiantRepository;
    private final ClasseRepository classeRepository;
    private final NoteRepository noteRepository;
    private final PresenceRepository presenceRepository;
    private final ModelMapper modelMapper;

    public EtudiantDTO creerEtudiant(EtudiantDTO etudiantDTO) {
        Class classe = classeRepository.findById(etudiantDTO.getClasseId())
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        Etudiant etudiant = modelMapper.map(etudiantDTO, Etudiant.class);
        etudiant.setClasse(classe);
        etudiant.setDateInscription(LocalDate.now());
        etudiant.setActif(true);

        if (etudiant.getNumeroEtudiant() == null) {
            etudiant.setNumeroEtudiant(genererNumeroEtudiant());
        }

        Etudiant saved = etudiantRepository.save(etudiant);
        return convertirEnDTO(saved);
    }

    public EtudiantDTO mettreAJourEtudiant(Long id, EtudiantDTO etudiantDTO) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        etudiant.setNom(etudiantDTO.getNom());
        etudiant.setPrenom(etudiantDTO.getPrenom());
        etudiant.setDateNaissance(etudiantDTO.getDateNaissance());
        etudiant.setEmail(etudiantDTO.getEmail());
        etudiant.setTelephone(etudiantDTO.getTelephone());
        etudiant.setGenre(etudiantDTO.getGenre());

        if (etudiantDTO.getClasseId() != null && !etudiantDTO.getClasseId().equals(etudiant.getClasse().getId())) {
            Class nouvelleClasse = classeRepository.findById(etudiantDTO.getClasseId())
                    .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
            etudiant.setClasse(nouvelleClasse);
        }

        Etudiant updated = etudiantRepository.save(etudiant);
        return convertirEnDTO(updated);
    }

    public void supprimerEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        etudiant.setActif(false);
        etudiantRepository.save(etudiant);
    }

    public EtudiantDTO obtenirEtudiant(Long id) {
        Etudiant etudiant = etudiantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));
        return convertirEnDTO(etudiant);
    }

    public List<EtudiantDTO> obtenirTousLesEtudiants() {
        return etudiantRepository.findAll().stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public List<EtudiantDTO> obtenirEtudiantsParClasse(Long classeId) {
        return etudiantRepository.findByClasseId(classeId).stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public List<EtudiantDTO> rechercherEtudiants(String recherche) {
        return etudiantRepository.findByNomContainingIgnoreCaseOrPrenomContainingIgnoreCase(recherche, recherche)
                .stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    private EtudiantDTO convertirEnDTO(Etudiant etudiant) {
        EtudiantDTO dto = modelMapper.map(etudiant, EtudiantDTO.class);
        dto.setClasseId(etudiant.getClasse().getId());
        dto.setClasseNom(etudiant.getClasse().getNom());

        Double moyenne = noteRepository.calculerMoyenneGenerale(etudiant.getId());
        dto.setMoyenneGenerale(moyenne != null ? moyenne : 0.0);

        Long absences = presenceRepository.countByEtudiantIdAndStatut(
                etudiant.getId(), StatutPresence.ABSENT);
        dto.setNombreAbsences(absences != null ? absences.intValue() : 0);

        return dto;
    }

    private String genererNumeroEtudiant() {
        String annee = String.valueOf(LocalDate.now().getYear()).substring(2);
        long count = etudiantRepository.count() + 1;
        return "ETU" + annee + String.format("%05d", count);
    }
}

