package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "notes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "etudiant_id", nullable = false)
    private Etudiant etudiant;

    @ManyToOne
    @JoinColumn(name = "activite_id", nullable = false)
    private Activite activite;

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;

    @Column(nullable = false)
    private Double valeur; // Note sur 20

    @Column(nullable = false)
    private Double coefficient = 1.0;

    @Enumerated(EnumType.STRING)
    @Column(name = "type_evaluation", nullable = false)
    private TypeEvaluation typeEvaluation;

    @Column(name = "date_evaluation", nullable = false)
    private LocalDate dateEvaluation;

    private String commentaire;

    @Column(name = "note_technique")
    private Double noteTechnique;

    @Column(name = "note_pratique")
    private Double notePratique;

    @Column(name = "note_comportement")
    private Double noteComportement;
}
