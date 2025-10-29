package fstt.ma.appdistribuee.ejb;

import fstt.ma.appdistribuee.entities.Etudiant;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class EtudiantImpl implements EtudiantRemote {

    @PersistenceContext(unitName = "mycnx")
    private EntityManager em;

    @Override
    public void ajouterEtudiant(Etudiant etudiant) {
        em.persist(etudiant);
    }

    @Override
    public Etudiant SearchEtudiant(Long id) {
        return em.find(Etudiant.class, id);

    }

    @Override
    public List<Etudiant> getEtudiants() {
        return em.createQuery("select e from Etudiant e").getResultList();
    }

    @Override
    public void modifierEtudiant(Etudiant etudiant) {
        em.merge(etudiant);
    }

    @Override
    public void supprimerEtudiant(Long id) {
        Etudiant etudiant = em.find(Etudiant.class, id);
        if (etudiant != null) {
            em.remove(etudiant);
        }
    }


    @Override
    public Etudiant searchEtudiantCNE(String cne) {
        TypedQuery<Etudiant> query = em.createQuery(
                "SELECT e FROM Etudiant e WHERE e.cne = :cne",
                Etudiant.class
        );
        query.setParameter("cne", cne);
        List<Etudiant> resultats = query.getResultList();
        return resultats.isEmpty() ? null : resultats.get(0);

    }

}