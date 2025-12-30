package SmartEtuSport.dto;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteParActiviteDTO {
    private String activiteNom;
    private Double coefficient;
    private List<NoteDTO> notes;
    private Double moyenne;
    private String appreciation;
}
