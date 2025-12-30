package SmartEtuSport.dto;

import SmartEtuSport.entity.StatutPresence;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PresenceDTO {
    private Long id;

    @NotNull(message = "L'étudiant est obligatoire")
    private Long etudiantId;

    private String etudiantNom;

    @NotNull(message = "La session est obligatoire")
    private Long sessionId;

    private String sessionTitre;

    @NotNull(message = "Le statut est obligatoire")
    private StatutPresence statut;

    private LocalDateTime heureArrivee;
    private String justification;
    private String documentJustificatif;
}
