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
import java.util.List;


@WebServlet(value = "/liste-etudiants")
public class ListeEtudiantServlet extends HttpServlet {

    @EJB(mappedName = "ejb:/ejb-module/EtudiantEmpl!com.etu.services.EtudiantRemote")
    private EtudiantRemote etudiantEmpl;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Etudiant> etudiants = etudiantEmpl.getEtudiants();
        request.setAttribute("etudiants", etudiants);
        request.getRequestDispatcher("/etudiant/liste.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

}
