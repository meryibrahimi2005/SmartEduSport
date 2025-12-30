package SmartEtuSport.dto;

import SmartEtuSport.entity.Role;
import lombok.*;
import jakarta.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProfesseurDTO {
    private Long id;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    @NotBlank(message = "Le prénom est obligatoire")
    private String prenom;

    @NotBlank(message = "L'email est obligatoire")
    @Email(message = "Email invalide")
    private String email;

    private String telephone;

    @NotBlank(message = "La spécialité est obligatoire")
    private String specialite;

    @NotNull(message = "Le rôle est obligatoire")
    private Role role;

    private Boolean actif;
}
