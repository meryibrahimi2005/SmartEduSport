package SmartEtuSport.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StatistiqueDTO {
    private Long etudiantId;
    private String etudiantNom;
    private Double moyenneGenerale;
    private Double moyennePonderee;
    private Integer nombreNotes;
    private Integer nombrePresences;
    private Integer nombreAbsences;
    private Integer nombreRetards;
    private Double tauxPresence;
    private String appreciation;
}
