package fstt.getudiants.ejb;

import fstt.getudiants.entities.SuiviId;
import fstt.getudiants.entities.Suivie;
import fstt.getudiants.entities.Etudiant;
import fstt.getudiants.entities.Module;
import jakarta.ejb.EJBException;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.Date;
import java.util.List;

@Stateless
public class SuivieImpl implements SuivieRemote {

    @PersistenceContext(unitName = "mycnx")
    private EntityManager em;

    @Override
    public void ajouterSuivie(Suivie suivie) {
        try {
            em.merge(suivie);
        } catch (Exception e) {
            throw new EJBException("Erreur lors de l'ajout du suivi", e);
        }
    }

    // NOUVELLE méthode pour ajouter avec IDs
    @Override
    public void ajouterSuivie(Long idModule, Long idEtudiant, Double note) {
        try {
            // Récupérer les entités
            Etudiant etudiant = em.find(Etudiant.class, idEtudiant);
            Module module = em.find(Module.class, idModule);

            if (etudiant == null || module == null) {
                throw new IllegalArgumentException("Étudiant ou Module non trouvé");
            }

            // Vérifier si le suivi existe déjà
            SuiviId suiviId = new SuiviId(idModule, idEtudiant);
            Suivie existingSuivie = em.find(Suivie.class, suiviId);

            if (existingSuivie != null) {
                // Mettre à jour le suivi existant
                existingSuivie.setNote(note);
                existingSuivie.setDateNote(new Date());
                em.merge(existingSuivie);
            } else {
                // Créer un nouveau suivi
                Suivie suivie = new Suivie();
                suivie.setModule(module);
                suivie.setEtudiant(etudiant);
                suivie.setNote(note);
                suivie.setDateNote(new Date());
                em.merge(suivie);
            }
        } catch (Exception e) {
            throw new EJBException("Erreur lors de l'ajout du suivi", e);
        }
    }

    @Override
    public void modifierSuivie(Suivie suivie){
        em.merge(suivie);
    }

    // NOUVELLE méthode pour modifier avec IDs
    @Override
    public void modifierSuivie(Long idModule, Long idEtudiant, Double note) {
        try {
            SuiviId suiviId = new SuiviId(idModule, idEtudiant);
            Suivie suivie = em.find(Suivie.class, suiviId);

            if (suivie != null) {
                suivie.setNote(note);
                suivie.setDateNote(new Date());
                em.merge(suivie);
            } else {
                throw new IllegalArgumentException("Note non trouvée");
            }
        } catch (Exception e) {
            throw new EJBException("Erreur lors de la modification du suivi", e);
        }
    }

    @Override
    public void supprimerSuivie(SuiviId id){
        Suivie suivie = em.find(Suivie.class, id);
        if(suivie != null){
            em.remove(suivie);
        }
    }

    // NOUVELLE méthode pour supprimer avec IDs séparés
    @Override
    public void supprimerSuivie(Long idModule, Long idEtudiant) {
        try {
            SuiviId suiviId = new SuiviId(idModule, idEtudiant);
            Suivie suivie = em.find(Suivie.class, suiviId);

            if (suivie != null) {
                em.remove(suivie);
            } else {
                throw new IllegalArgumentException("Note non trouvée");
            }
        } catch (Exception e) {
            throw new EJBException("Erreur lors de la suppression du suivi", e);
        }
    }

    @Override
    public Suivie chercherSuivie(SuiviId id){
        return em.find(Suivie.class, id);
    }

    @Override
    public List<Suivie> getSuivies(){
        TypedQuery<Suivie> query = em.createQuery(
                "SELECT s FROM Suivie s ORDER BY s.dateNote DESC",
                Suivie.class
        );
        return query.getResultList();
    }

    @Override
    public List<Suivie> getSuiviesParEtudiant(Long idEtudiant){
        TypedQuery<Suivie> query = em.createQuery(
                "SELECT s FROM Suivie s WHERE s.etudiant.idetudiant = :id ORDER BY s.dateNote DESC",
                Suivie.class
        );
        query.setParameter("id", idEtudiant);
        return query.getResultList();
    }

    @Override
    public List<Suivie> getSuiviesParModule(Long idModule){
        TypedQuery<Suivie> query = em.createQuery(
                "SELECT s FROM Suivie s WHERE s.module.idmodule = :id ORDER BY s.note DESC",
                Suivie.class
        );
        query.setParameter("id", idModule);
        return query.getResultList();
    }
}
