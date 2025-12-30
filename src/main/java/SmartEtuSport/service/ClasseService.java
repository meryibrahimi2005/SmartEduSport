package SmartEtuSport.service;

import SmartEtuSport.dto.ClasseDTO;
import SmartEtuSport.entity.Class;
import SmartEtuSport.entity.NiveauAcademique;
import SmartEtuSport.entity.Professeur;
import SmartEtuSport.repository.ClasseRepository;
import SmartEtuSport.repository.NiveauAcademiqueRepository;
import SmartEtuSport.repository.ProfesseurRepository;
import SmartEtuSport.repository.EtudiantRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ClasseService {

    private final ClasseRepository classeRepository;
    private final NiveauAcademiqueRepository niveauAcademiqueRepository;
    private final ProfesseurRepository professeurRepository;
    private final EtudiantRepository etudiantRepository;
    private final ModelMapper modelMapper;

    public ClasseDTO creerClasse(ClasseDTO classeDTO) {
        NiveauAcademique niveau = niveauAcademiqueRepository.findById(classeDTO.getNiveauAcademiqueId())
                .orElseThrow(() -> new RuntimeException("Niveau académique non trouvé"));

        Class classe = modelMapper.map(classeDTO, Class.class);
        classe.setNiveauAcademique(niveau);

        if (classeDTO.getProfesseurId() != null) {
            Professeur professeur = professeurRepository.findById(classeDTO.getProfesseurId())
                    .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));
            classe.setProfesseur(professeur);
        }

        Class saved = classeRepository.save(classe);
        return convertirEnDTO(saved);
    }

    public ClasseDTO mettreAJourClasse(Long id, ClasseDTO classeDTO) {
        Class classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));

        classe.setNom(classeDTO.getNom());
        classe.setAnneeScolaire(classeDTO.getAnneeScolaire());
        classe.setEffectifMax(classeDTO.getEffectifMax());

        if (classeDTO.getNiveauAcademiqueId() != null) {
            NiveauAcademique niveau = niveauAcademiqueRepository.findById(classeDTO.getNiveauAcademiqueId())
                    .orElseThrow(() -> new RuntimeException("Niveau académique non trouvé"));
            classe.setNiveauAcademique(niveau);
        }

        if (classeDTO.getProfesseurId() != null) {
            Professeur professeur = professeurRepository.findById(classeDTO.getProfesseurId())
                    .orElseThrow(() -> new RuntimeException("Professeur non trouvé"));
            classe.setProfesseur(professeur);
        }

        Class updated = classeRepository.save(classe);
        return convertirEnDTO(updated);
    }

    public void supprimerClasse(Long id) {
        classeRepository.deleteById(id);
    }

    public ClasseDTO obtenirClasse(Long id) {
        Class classe = classeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classe non trouvée"));
        return convertirEnDTO(classe);
    }

    public List<ClasseDTO> obtenirToutesLesClasses() {
        return classeRepository.findAll().stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public List<ClasseDTO> obtenirClassesParNiveau(Long niveauId) {
        return classeRepository.findByNiveauAcademiqueId(niveauId).stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public List<ClasseDTO> obtenirClassesParAnneeScolaire(String anneeScolaire) {
        return classeRepository.findByAnneeScolaire(anneeScolaire).stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    private ClasseDTO convertirEnDTO(Class classe) {
        ClasseDTO dto = modelMapper.map(classe, ClasseDTO.class);
        dto.setNiveauAcademiqueId(classe.getNiveauAcademique().getId());
        dto.setNiveauAcademiqueNom(classe.getNiveauAcademique().getNom());

        if (classe.getProfesseur() != null) {
            dto.setProfesseurId(classe.getProfesseur().getId());
            dto.setProfesseurNom(classe.getProfesseur().getNom() + " " + classe.getProfesseur().getPrenom());
        }

        Long effectif = etudiantRepository.countByClasseId(classe.getId());
        dto.setEffectifActuel(effectif.intValue());

        return dto;
    }
}