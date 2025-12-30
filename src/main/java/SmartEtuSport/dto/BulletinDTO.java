package SmartEtuSport.dto;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BulletinDTO {
    private EtudiantDTO etudiant;
    private ClasseDTO classe;
    private SemestreDTO semestre;
    private List<NoteParActiviteDTO> notesParActivite;
    private Double moyenneGenerale;
    private Double moyennePonderee;
    private Integer nombreAbsences;
    private Integer nombreRetards;
    private String appreciationGenerale;
    private LocalDate dateGeneration;
}