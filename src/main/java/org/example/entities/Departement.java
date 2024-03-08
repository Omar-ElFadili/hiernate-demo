package org.example.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Departement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    @OneToMany(mappedBy = "departement")
    private List<Employee> employees;

    public Departement(String nom, List<Employee> employees) {
        this.nom = nom;
        this.employees = employees;
    }
}
