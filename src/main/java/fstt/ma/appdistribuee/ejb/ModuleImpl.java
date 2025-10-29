package fstt.ma.appdistribuee.ejb;

import fstt.ma.appdistribuee.entities.Module;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import java.util.List;

@Stateless
public class ModuleImpl implements  ModuleRemote {
    @PersistenceContext(unitName = "mycnx")
    private EntityManager em;


    @Override
    public  void ajouterModule(Module module ){
        em.persist(module);
    }

    @Override
    public void modifierModule(Module module){
        em.merge(module);
    }

    @Override
    public void supprimerModule(Long id){
        Module module = em.find(Module.class, id);
        em.remove(module);
    }

    @Override
    public List<Module> getModules(){
        TypedQuery<Module> query = em.createQuery(
                "SELECT m FROM Module m ORDER BY m.nommodule",
                Module.class
        );
        return query.getResultList();
    }

    @Override
    public  Module chercherModule(Long id){
        return em.find(Module.class, id);
    }
}
