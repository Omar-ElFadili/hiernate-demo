package org.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    @ManyToOne
    @JoinColumn(name = "departement_id")
    private Departement departement;

    public Employee(String nom, String prenom, String email, Departement departement) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.departement = departement;
    }
}
