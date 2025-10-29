package fstt.getudiants.ejb;
import fstt.getudiants.entities.SuiviId;
import fstt.getudiants.entities.Suivie;

import jakarta.ejb.Remote;

import java.util.List;

@Remote
public interface SuivieRemote {

    // create
    void ajouterSuivie(Suivie suivie);

    // read
    Suivie chercherSuivie(SuiviId id);
    List<Suivie> getSuivies();
    List<Suivie> getSuiviesParEtudiant(Long idEtudiant);
    List<Suivie> getSuiviesParModule(Long idModule);
    public void ajouterSuivie(Long idModule, Long idEtudiant, Double note);
    public void supprimerSuivie(SuiviId id);
    public void modifierSuivie(Long idModule, Long idEtudiant, Double note);



        // update
    void modifierSuivie(Suivie suivie);

    // delete
    void supprimerSuivie(Long idModule, Long idEtudiant);

}
