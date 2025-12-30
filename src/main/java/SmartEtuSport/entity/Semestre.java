package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "semestres")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Semestre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom; // Semestre 1, Semestre 2

    @Column(nullable = false)
    private Integer numero; // 1 ou 2

    @Column(name = "annee_scolaire", nullable = false)
    private String anneeScolaire;

    @Column(name = "date_debut", nullable = false)
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    private LocalDate dateFin;

    @OneToMany(mappedBy = "semestre", cascade = CascadeType.ALL)
    private Set<Session> sessions;

    @Column(name = "actif")
    private Boolean actif = true;
}
