package fstt.ma.appdistribuee.Controller.Etudiant;

import fstt.ma.appdistribuee.ejb.EtudiantRemote;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(value = "/supprimer-etudiant")

public class SupprimerEtudiantServlet extends HttpServlet {
    @EJB(mappedName = "ejb:/ejb-module/EtudiantEmpl!com.etu.services.EtudiantRemote")
    private EtudiantRemote etudiantEmpl;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id_etudiant"));
        etudiantEmpl.supprimerEtudiant(id);
        response.sendRedirect("liste-etudiants");


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
