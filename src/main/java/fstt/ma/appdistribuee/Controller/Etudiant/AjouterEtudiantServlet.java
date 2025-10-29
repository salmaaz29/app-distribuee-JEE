package fstt.ma.appdistribuee.Controller.Etudiant;


import fstt.ma.appdistribuee.ejb.EtudiantRemote;
import fstt.ma.appdistribuee.entities.Etudiant;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/ajouter-etudiant")
public class AjouterEtudiantServlet extends HttpServlet {
    @EJB(mappedName = "ejb:/ejb-module/EtudiantEmpl!com.etu.services.EtudiantRemote")
    private EtudiantRemote etudiantEmpl;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/etudiant/ajouter.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String cne = request.getParameter("cne");
        String adresse = request.getParameter("adresse");
        String niveau = request.getParameter("niveau");

        Etudiant etudiant = new Etudiant(nom, prenom, cne, adresse, niveau);
        etudiantEmpl.ajouterEtudiant(etudiant);

        response.sendRedirect("liste-etudiants");
    }
}
