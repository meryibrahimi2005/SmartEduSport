package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "fiches_medicales")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FicheMedicale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "etudiant_id", nullable = false, unique = true)
    private Etudiant etudiant;

    @Column(name = "groupe_sanguin")
    private String groupeSanguin;

    @Column(columnDefinition = "TEXT")
    private String allergies;

    @Column(name = "contre_indications", columnDefinition = "TEXT")
    private String contreIndications;

    @Column(columnDefinition = "TEXT")
    private String medicaments;

    @Column(name = "contact_urgence_nom")
    private String contactUrgenceNom;

    @Column(name = "contact_urgence_telephone")
    private String contactUrgenceTelephone;

    @Column(name = "date_derniere_mise_a_jour")
    private LocalDate dateDerniereMiseAJour;

    @Column(columnDefinition = "TEXT")
    private String remarques;
}
