package fstt.ma.appdistribuee.ejb;


import jakarta.ejb.Remote;
import java.util.List;
import fstt.ma.appdistribuee.entities.Module;

@Remote
public interface ModuleRemote {

    // create
    void ajouterModule(Module module );

    // read
    List<Module> getModules();
    Module chercherModule(Long id);

    // update
    void modifierModule(Module module);

    // delete
    void supprimerModule(Long id);
}
