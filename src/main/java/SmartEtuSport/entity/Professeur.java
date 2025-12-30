package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "professeurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Professeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String motDePasse;

    private String telephone;

    @Column(nullable = false)
    private String specialite; // Sport de spécialité

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private Set<Class> classes;

    @OneToMany(mappedBy = "professeur", cascade = CascadeType.ALL)
    private Set<Session> sessions;

    @Column(name = "actif")
    private Boolean actif = true;
}
