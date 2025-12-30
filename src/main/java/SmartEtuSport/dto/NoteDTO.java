package SmartEtuSport.dto;

import SmartEtuSport.entity.TypeEvaluation;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDTO {
    private Long id;

    @NotNull(message = "L'étudiant est obligatoire")
    private Long etudiantId;

    private String etudiantNom;

    @NotNull(message = "L'activité est obligatoire")
    private Long activiteId;

    private String activiteNom;
    private Long sessionId;

    @NotNull(message = "La valeur de la note est obligatoire")
    @Min(value = 0, message = "La note doit être au minimum 0")
    @Max(value = 20, message = "La note doit être au maximum 20")
    private Double valeur;

    @NotNull(message = "Le coefficient est obligatoire")
    @Min(value = 0, message = "Le coefficient doit être positif")
    private Double coefficient;

    @NotNull(message = "Le type d'évaluation est obligatoire")
    private TypeEvaluation typeEvaluation;

    @NotNull(message = "La date d'évaluation est obligatoire")
    private LocalDate dateEvaluation;

    private String commentaire;
    private Double noteTechnique;
    private Double notePratique;
    private Double noteComportement;
}
