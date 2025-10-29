package fstt.getudiants.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "etudiant")
@Getter
@Setter
@NoArgsConstructor

public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_etudiant")
    private Long idetudiant;

    @Column(name = "nom")
    private String nom;
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "cne")
    private String cne;
    @Column(name = "niveau")
    private String niveau;

    public Etudiant(String nom, String prenom, String adresse, String cne, String niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cne = cne;
        this.niveau = niveau;
    }


}
