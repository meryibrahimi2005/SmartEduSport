package SmartEtuSport.dto;

import SmartEtuSport.entity.TypeSport;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActiviteDTO {
    private Long id;

    @NotBlank(message = "Le nom de l'activité est obligatoire")
    private String nom;

    @NotNull(message = "Le type de sport est obligatoire")
    private TypeSport typeSport;

    private String description;

    @NotNull(message = "Le coefficient est obligatoire")
    @Min(value = 0, message = "Le coefficient doit être positif")
    private Double coefficient;

    private String materielRequis;
    private Boolean actif;
}
