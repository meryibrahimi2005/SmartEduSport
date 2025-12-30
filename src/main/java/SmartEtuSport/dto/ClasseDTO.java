package SmartEtuSport.dto;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClasseDTO {
    private Long id;

    @NotBlank(message = "Le nom de la classe est obligatoire")
    private String nom;

    @NotBlank(message = "L'année scolaire est obligatoire")
    private String anneeScolaire;

    @NotNull(message = "Le niveau académique est obligatoire")
    private Long niveauAcademiqueId;

    private String niveauAcademiqueNom;
    private Long professeurId;
    private String professeurNom;
    private Integer effectifMax;
    private Integer effectifActuel;
}
