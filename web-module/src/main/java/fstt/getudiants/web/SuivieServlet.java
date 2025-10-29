package fstt.getudiants.web;
import fstt.getudiants.ejb.EtudiantRemote;
import fstt.getudiants.ejb.ModuleRemote;
import fstt.getudiants.ejb.SuivieRemote;
import fstt.getudiants.entities.Etudiant;
import fstt.getudiants.entities.Module;
import fstt.getudiants.entities.SuiviId;
import fstt.getudiants.entities.Suivie;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@WebServlet("/suivies")
public class SuivieServlet extends HttpServlet {

    @EJB(lookup = "java:global/ejb-module-1.0-SNAPSHOT/SuivieImpl!fstt.getudiants.ejb.SuivieRemote")
    private SuivieRemote suivieService;

    @EJB(lookup = "java:global/ejb-module-1.0-SNAPSHOT/EtudiantImpl!fstt.getudiants.ejb.EtudiantRemote")
    private EtudiantRemote etudiantService;

    @EJB(lookup = "java:global/ejb-module-1.0-SNAPSHOT/ModuleImpl!fstt.getudiants.ejb.ModuleRemote")
    private ModuleRemote moduleService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            // Mode édition - charger le suivi à modifier
            String idModuleParam = req.getParameter("idModule");
            String idEtudiantParam = req.getParameter("idEtudiant");

            if (idModuleParam != null && !idModuleParam.trim().isEmpty() &&
                    idEtudiantParam != null && !idEtudiantParam.trim().isEmpty()) {
                try {
                    Long idModule = Long.parseLong(idModuleParam);
                    Long idEtudiant = Long.parseLong(idEtudiantParam);

                    SuiviId suiviId = new SuiviId(idModule, idEtudiant);
                    Suivie suivie = suivieService.chercherSuivie(suiviId);

                    if (suivie != null) {
                        req.setAttribute("suivieEdit", suivie);
                    } else {
                        req.setAttribute("error", "Note non trouvée");
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide");
                }
            } else {
                req.setAttribute("error", "IDs manquants pour la modification");
            }
        }

        // Charger les données
        List<Suivie> suivies = suivieService.getSuivies();
        List<Etudiant> etudiants = etudiantService.getEtudiants();
        List<Module> modules = moduleService.getModules();

        // Passer à la JSP
        req.setAttribute("suivies", suivies);
        req.setAttribute("etudiants", etudiants);
        req.setAttribute("modules", modules);

        req.getRequestDispatcher("suivies.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("add".equals(action)) {
            try {
                // Récupérer les données
                Long idEtudiant = Long.parseLong(req.getParameter("etudiant"));
                Long idModule = Long.parseLong(req.getParameter("module"));
                Double note = Double.parseDouble(req.getParameter("note"));

                // Utiliser la méthode qui accepte les IDs
                suivieService.ajouterSuivie(idModule, idEtudiant, note);
                req.setAttribute("success", "Note ajoutée avec succès!");

            } catch (Exception e) {
                req.setAttribute("error", "Erreur lors de l'ajout de la note: " + e.getMessage());
            }

        } else if ("update".equals(action)) {
            try {
                // Récupérer les données
                Long idEtudiant = Long.parseLong(req.getParameter("idEtudiant"));
                Long idModule = Long.parseLong(req.getParameter("idModule"));
                Double note = Double.parseDouble(req.getParameter("note"));

                // Modifier la note
                suivieService.modifierSuivie(idModule, idEtudiant, note);
                req.setAttribute("success", "Note modifiée avec succès!");

            } catch (Exception e) {
                req.setAttribute("error", "Erreur lors de la modification de la note: " + e.getMessage());
            }

        } else if ("delete".equals(action)) {
            try {
                // Récupérer les IDs
                Long idEtudiant = Long.parseLong(req.getParameter("idEtudiant"));
                Long idModule = Long.parseLong(req.getParameter("idModule"));

                // Supprimer le suivi
                suivieService.supprimerSuivie(idModule, idEtudiant);
                req.setAttribute("success", "Note supprimée avec succès!");

            } catch (Exception e) {
                req.setAttribute("error", "Erreur lors de la suppression de la note: " + e.getMessage());
            }
        }

        doGet(req, resp);
    }
}