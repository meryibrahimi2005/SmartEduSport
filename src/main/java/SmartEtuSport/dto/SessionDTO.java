package SmartEtuSport.dto;

import SmartEtuSport.entity.TypeSession;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SessionDTO {
    private Long id;

    @NotBlank(message = "Le titre est obligatoire")
    private String titre;

    @NotNull(message = "La date et l'heure sont obligatoires")
    private LocalDateTime dateHeure;

    @NotNull(message = "La durée est obligatoire")
    @Min(value = 1, message = "La durée doit être au minimum 1 minute")
    private Integer duree;

    @NotNull(message = "Le type de session est obligatoire")
    private TypeSession type;

    @NotNull(message = "La classe est obligatoire")
    private Long classeId;

    private String classeNom;

    @NotNull(message = "Le semestre est obligatoire")
    private Long semestreId;

    private String semestreNom;
    private Long activiteId;
    private String activiteNom;

    @NotNull(message = "Le professeur est obligatoire")
    private Long professeurId;

    private String professeurNom;
    private String lieu;
    private String description;
    private Integer nombrePresents;
    private Integer nombreAbsents;
}
