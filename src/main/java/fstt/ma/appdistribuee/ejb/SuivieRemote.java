package fstt.ma.appdistribuee.ejb;


import fstt.ma.appdistribuee.entities.SuiviId;
import fstt.ma.appdistribuee.entities.Suivie;
import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface SuivieRemote {

    // create
    void ajouterSuivie(Suivie suivie);

    // read
    Suivie chercherSuivie(Long id);
    List<Suivie> getSuivies();
    List<Suivie> getSuiviesParEtudiant(Long idEtudiant);
    List<Suivie> getSuiviesParModule(Long idModule);

    // update
    void modifierSuivie(Suivie suivie);

    // delete
    void supprimerSuivie(SuiviId id);

}
