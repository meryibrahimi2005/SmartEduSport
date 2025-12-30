package SmartEtuSport.dto;

import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SemestreDTO {
    private Long id;

    @NotBlank(message = "Le nom du semestre est obligatoire")
    private String nom;

    @NotNull(message = "Le numéro du semestre est obligatoire")
    @Min(value = 1, message = "Le numéro doit être 1 ou 2")
    @Max(value = 2, message = "Le numéro doit être 1 ou 2")
    private Integer numero;

    @NotBlank(message = "L'année scolaire est obligatoire")
    private String anneeScolaire;

    @NotNull(message = "La date de début est obligatoire")
    private LocalDate dateDebut;

    @NotNull(message = "La date de fin est obligatoire")
    private LocalDate dateFin;

    private Boolean actif;
}
