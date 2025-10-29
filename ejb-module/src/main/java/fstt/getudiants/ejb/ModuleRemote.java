package fstt.getudiants.ejb;

import fstt.getudiants.entities.Module;
import jakarta.ejb.Remote;
import java.util.List;




@Remote
public interface ModuleRemote {

    // create
    void ajouterModule(Module module);

    // read
    List<Module> getModules();
    Module chercherModule(Long id);

    // update
    void modifierModule(Module module);

    // delete
    void supprimerModule(Long id);
}
