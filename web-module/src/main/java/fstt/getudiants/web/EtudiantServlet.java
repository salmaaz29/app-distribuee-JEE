package fstt.getudiants.web;

import fstt.getudiants.ejb.EtudiantRemote;
import fstt.getudiants.entities.Etudiant;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/etudiants")
public class EtudiantServlet extends HttpServlet {

    @EJB(lookup = "java:global/ejb-module-1.0-SNAPSHOT/EtudiantImpl!fstt.getudiants.ejb.EtudiantRemote")
    private EtudiantRemote etudiantService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String action = req.getParameter("action");

        if ("edit".equals(action)) {
            // Mode édition - charger l'étudiant à modifier
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    Etudiant etudiant = etudiantService.SearchEtudiant(id);
                    if (etudiant != null) {
                        req.setAttribute("etudiantEdit", etudiant);
                    } else {
                        req.setAttribute("error", "Étudiant non trouvé avec l'ID: " + id);
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide: " + idParam);
                }
            } else {
                req.setAttribute("error", "ID manquant pour la modification");
            }
        }

        List<Etudiant> liste = etudiantService.getEtudiants();
        req.setAttribute("etudiants", liste);
        req.getRequestDispatcher("etudiants.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getParameter("action");

        if ("add".equals(action)) {
            // Ajouter un nouvel étudiant
            Etudiant e = new Etudiant();
            e.setNom(req.getParameter("nom"));
            e.setPrenom(req.getParameter("prenom"));
            e.setCne(req.getParameter("cne"));
            e.setAdresse(req.getParameter("adresse"));
            e.setNiveau(req.getParameter("niveau"));
            etudiantService.ajouterEtudiant(e);

        } else if ("update".equals(action)) {
            // Modifier un étudiant existant
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    Etudiant e = etudiantService.SearchEtudiant(id);

                    if (e != null) {
                        e.setNom(req.getParameter("nom"));
                        e.setPrenom(req.getParameter("prenom"));
                        e.setCne(req.getParameter("cne"));
                        e.setAdresse(req.getParameter("adresse"));
                        e.setNiveau(req.getParameter("niveau"));
                        etudiantService.modifierEtudiant(e);
                        req.setAttribute("success", "Étudiant modifié avec succès!");
                    } else {
                        req.setAttribute("error", "Étudiant non trouvé");
                    }
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide: " + idParam);
                }
            }

        } else if ("delete".equals(action)) {
            // Supprimer un étudiant
            String idParam = req.getParameter("id");
            if (idParam != null && !idParam.trim().isEmpty()) {
                try {
                    Long id = Long.parseLong(idParam);
                    etudiantService.supprimerEtudiant(id);
                    req.setAttribute("success", "Étudiant supprimé avec succès!");
                } catch (NumberFormatException e) {
                    req.setAttribute("error", "ID invalide: " + idParam);
                }
            }
        }

        doGet(req, resp);
    }
}