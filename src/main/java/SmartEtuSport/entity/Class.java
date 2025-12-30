package SmartEtuSport.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Table(name = "classes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom; // 1A, 2B, Terminale S, etc.

    @Column(name = "annee_scolaire", nullable = false)
    private String anneeScolaire; // 2024-2025

    @ManyToOne
    @JoinColumn(name = "niveau_academique_id", nullable = false)
    private NiveauAcademique niveauAcademique;

    @ManyToOne
    @JoinColumn(name = "professeur_id")
    private Professeur professeur;

    @Column(name = "effectif_max")
    private Integer effectifMax;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private Set<Etudiant> etudiants;

    @OneToMany(mappedBy = "classe", cascade = CascadeType.ALL)
    private Set<Session> sessions;

}
