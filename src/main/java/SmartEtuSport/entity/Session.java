package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "sessions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(name = "date_heure", nullable = false)
    private LocalDateTime dateHeure;

    @Column(nullable = false)
    private Integer duree; // en minutes

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeSession type;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Class classe;

    @ManyToOne
    @JoinColumn(name = "semestre_id", nullable = false)
    private Semestre semestre;

    @ManyToOne
    @JoinColumn(name = "activite_id")
    private Activite activite;

    @ManyToOne
    @JoinColumn(name = "professeur_id", nullable = false)
    private Professeur professeur;

    private String lieu;

    private String description;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private Set<Presence> presences;

    @OneToMany(mappedBy = "session", cascade = CascadeType.ALL)
    private Set<Note> notes;
}

