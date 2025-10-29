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

@WebServlet(value = "/modifier-etudiant")
public class ModifierEtudiantServlet extends HttpServlet {
    @EJB(mappedName = "ejb:/ejb-module/EtudiantEmpl!com.etu.services.EtudiantRemote")
    private EtudiantRemote etudiantEmpl;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      Long id = Long.parseLong(request.getParameter("id"));
      Etudiant etudiant = etudiantEmpl.SearchEtudiant(id);
      request.setAttribute("etudiant", etudiant);
      request.getRequestDispatcher("/etudiant/modifier.jsp").forward(request, response);

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String adresse = request.getParameter("adresse");
        String cne = request.getParameter("cne");
        String niveau = request.getParameter("niveau");

        Etudiant etudiant = new Etudiant(nom,prenom,adresse,cne,niveau);
        etudiant.setIdetudiant(id);
        etudiantEmpl.modifierEtudiant(etudiant);
        response.sendRedirect("liste-etudiants");

    }
}
