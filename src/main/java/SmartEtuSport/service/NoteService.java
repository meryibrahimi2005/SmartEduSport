package SmartEtuSport.service;

import SmartEtuSport.dto.NoteDTO;
import SmartEtuSport.entity.*;
import SmartEtuSport.repository.*;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class NoteService {

    private final NoteRepository noteRepository;
    private final EtudiantRepository etudiantRepository;
    private final ActiviteRepository activiteRepository;
    private final SessionRepository sessionRepository;
    private final ModelMapper modelMapper;

    public NoteDTO creerNote(NoteDTO noteDTO) {
        Etudiant etudiant = etudiantRepository.findById(noteDTO.getEtudiantId())
                .orElseThrow(() -> new RuntimeException("Étudiant non trouvé"));

        Activite activite = activiteRepository.findById(noteDTO.getActiviteId())
                .orElseThrow(() -> new RuntimeException("Activité non trouvée"));

        Note note = modelMapper.map(noteDTO, Note.class);
        note.setEtudiant(etudiant);
        note.setActivite(activite);

        if (noteDTO.getSessionId() != null) {
            Session session = sessionRepository.findById(noteDTO.getSessionId())
                    .orElseThrow(() -> new RuntimeException("Session non trouvée"));
            note.setSession(session);
        }

        Note saved = noteRepository.save(note);
        return convertirEnDTO(saved);
    }

    public NoteDTO mettreAJourNote(Long id, NoteDTO noteDTO) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée"));

        note.setValeur(noteDTO.getValeur());
        note.setCoefficient(noteDTO.getCoefficient());
        note.setTypeEvaluation(noteDTO.getTypeEvaluation());
        note.setDateEvaluation(noteDTO.getDateEvaluation());
        note.setCommentaire(noteDTO.getCommentaire());
        note.setNoteTechnique(noteDTO.getNoteTechnique());
        note.setNotePratique(noteDTO.getNotePratique());
        note.setNoteComportement(noteDTO.getNoteComportement());

        Note updated = noteRepository.save(note);
        return convertirEnDTO(updated);
    }

    public void supprimerNote(Long id) {
        noteRepository.deleteById(id);
    }

    public NoteDTO obtenirNote(Long id) {
        Note note = noteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Note non trouvée"));
        return convertirEnDTO(note);
    }

    public List<NoteDTO> obtenirNotesParEtudiant(Long etudiantId) {
        return noteRepository.findByEtudiantId(etudiantId).stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public List<NoteDTO> obtenirNotesParActivite(Long activiteId) {
        return noteRepository.findByActiviteId(activiteId).stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public List<NoteDTO> obtenirNotesParSession(Long sessionId) {
        return noteRepository.findBySessionId(sessionId).stream()
                .map(this::convertirEnDTO)
                .collect(Collectors.toList());
    }

    public Double calculerMoyenneEtudiant(Long etudiantId) {
        Double moyenne = noteRepository.calculerMoyenneGenerale(etudiantId);
        return moyenne != null ? moyenne : 0.0;
    }

    public Double calculerMoyennePondereeEtudiant(Long etudiantId) {
        Double moyenne = noteRepository.calculerMoyennePonderee(etudiantId);
        return moyenne != null ? moyenne : 0.0;
    }

    private NoteDTO convertirEnDTO(Note note) {
        NoteDTO dto = modelMapper.map(note, NoteDTO.class);
        dto.setEtudiantId(note.getEtudiant().getId());
        dto.setEtudiantNom(note.getEtudiant().getNom() + " " + note.getEtudiant().getPrenom());
        dto.setActiviteId(note.getActivite().getId());
        dto.setActiviteNom(note.getActivite().getNom());

        if (note.getSession() != null) {
            dto.setSessionId(note.getSession().getId());
        }

        return dto;
    }
}
