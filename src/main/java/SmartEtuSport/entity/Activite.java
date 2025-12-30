package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "activites")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Activite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom; // Football, Basketball, Athlétisme, etc.

    @Enumerated(EnumType.STRING)
    @Column(name = "type_sport", nullable = false)
    private TypeSport typeSport;

    private String description;

    @Column(nullable = false)
    private Double coefficient = 1.0;

    @Column(name = "materiel_requis")
    private String materielRequis;

    @OneToMany(mappedBy = "activite", cascade = CascadeType.ALL)
    private Set<Session> sessions;

    @OneToMany(mappedBy = "activite", cascade = CascadeType.ALL)
    private Set<Note> notes;

    @Column(name = "actif")
    private Boolean actif = true;
}

