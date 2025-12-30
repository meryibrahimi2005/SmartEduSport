package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "etudiants")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Etudiant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(unique = true)
    private String email;

    private String telephone;

    @Column(name = "numero_etudiant", unique = true)
    private String numeroEtudiant;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "classe_id", nullable = false)
    private Class classe;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private Set<Note> notes;

    @OneToMany(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private Set<Presence> presences;

    @OneToOne(mappedBy = "etudiant", cascade = CascadeType.ALL)
    private FicheMedicale ficheMedicale;

    @Column(name = "date_inscription")
    private LocalDate dateInscription;

    @Column(name = "actif")
    private Boolean actif = true;
}

