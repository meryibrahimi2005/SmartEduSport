package SmartEtuSport.dto;

import SmartEtuSport.entity.Genre;
import lombok.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtudiantDTO {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    private LocalDate dateNaissance;

    @Email(message = "Email invalide")
    private String email;

    private String telephone;
    private String numeroEtudiant;
    private Genre genre;

    @NotNull(message = "La classe est obligatoire")
    private Long classeId;

    private String classeNom;
    private LocalDate dateInscription;
    private Boolean actif;
    private Double moyenneGenerale;
    private Integer nombreAbsences;
}