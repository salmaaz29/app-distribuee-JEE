package fstt.ma.appdistribuee.ejb;


import fstt.ma.appdistribuee.entities.Etudiant;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface EtudiantRemote {

    // create
    void ajouterEtudiant(Etudiant etudiant);

    // Read
    Etudiant SearchEtudiant(Long id);
    List<Etudiant> getEtudiants();
    Etudiant searchEtudiantCNE(String cne);

    // update
    void modifierEtudiant(Etudiant etudiant);

    // delete
    void supprimerEtudiant(Long id);

}
