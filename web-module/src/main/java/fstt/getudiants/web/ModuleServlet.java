package fstt.getudiants.web;

import fstt.getudiants.ejb.ModuleRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import fstt.getudiants.entities.Module;

import java.io.IOException;
import java.util.List;

@WebServlet("/modules")
public class ModuleServlet extends HttpServlet {

    @EJB(lookup = "java:global/ejb-module-1.0-SNAPSHOT/ModuleImpl!fstt.getudiants.ejb.ModuleRemote")
    private ModuleRemote moduleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            // charger le module à modifier
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    Module module = moduleService.chercherModule(id);
                    if (module != null) {
                        req.setAttribute("moduleEdit", module);
                    } else {
                        req.setAttribute("error", "Module non trouvé avec l'ID: " + id);
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide: " + idParam);
                }
            } else {
                req.setAttribute("error", "ID manquant pour la modification");
            }
        }

        List<Module> liste = moduleService.getModules();
        req.setAttribute("modules", liste);
        req.getRequestDispatcher("modules.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            // Ajouter un nouveau module
            Module m = new Module();
            m.setCodemodule(req.getParameter("code"));
            m.setNommodule(req.getParameter("nom"));
            moduleService.ajouterModule(m);
            req.setAttribute("success", "Module ajouté avec succès!");

        } else if ("update".equals(action)) {
            // Modifier un module existant
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    Module m = moduleService.chercherModule(id);

                    if (m != null) {
                        m.setCodemodule(req.getParameter("code"));
                        m.setNommodule(req.getParameter("nom"));
                        moduleService.modifierModule(m);
                        req.setAttribute("success", "Module modifié avec succès!");
                    } else {
                        req.setAttribute("error", "Module non trouvé");
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide: " + idParam);
                }
            }

        } else if ("delete".equals(action)) {
            // Supprimer un module
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    moduleService.supprimerModule(id);
                    req.setAttribute("success", "Module supprimé avec succès!");
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide: " + idParam);
                }
            }
        }

        doGet(req, resp);
    }
}