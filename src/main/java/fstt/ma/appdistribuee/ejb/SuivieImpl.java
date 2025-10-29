package fstt.ma.appdistribuee.ejb;

import fstt.ma.appdistribuee.entities.SuiviId;
import fstt.ma.appdistribuee.entities.Suivie;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class SuivieImpl implements SuivieRemote {

    @PersistenceContext(unitName = "mycnx")
    private EntityManager em;

    @Override
    public void ajouterSuivie(Suivie suivie){
        em.persist(suivie);
    }

    @Override
    public void modifierSuivie(Suivie suivie){
        em.merge(suivie);
    }

    @Override
    public void supprimerSuivie(SuiviId id){
        Suivie suivie = em.find(Suivie.class, id);
        if(suivie != null){
            em.remove(suivie);
        }
    }

    @Override
    public Suivie chercherSuivie(Long id){
        return em.find(Suivie.class, id);
    }

    @Override
    public  List<Suivie> getSuivies(){
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
