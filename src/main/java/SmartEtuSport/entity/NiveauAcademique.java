package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "niveaux_academiques")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class NiveauAcademique {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nom; // Primaire, Collège, Lycée, Université

    private String description;

    @Column(name = "ordre_affichage")
    private Integer ordreAffichage;

    @OneToMany(mappedBy = "niveauAcademique", cascade = CascadeType.ALL)
    private Set<Class> classes;

}
