package fstt.ma.appdistribuee.Controller;

import fstt.ma.appdistribuee.ejb.EtudiantRemote;
import fstt.ma.appdistribuee.entities.Etudiant;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/test-ejb")
public class ServletTestEJB  extends HttpServlet {
    @EJB(mappedName = "ejb:/ejb-module/EtudiantEmpl!com.etu.services.EtudiantRemote")
    private EtudiantRemote etudiantEmpl;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("<h1>Test EJB ÉtudiantEmpl</h1>");

        try {
            // Test d'ajout
            Etudiant etudiant = new Etudiant("Test", "User", "C12345", "Adresse test", "Master");
            etudiantEmpl.ajouterEtudiant(etudiant);
            out.println("<p>✅ Étudiant ajouté avec succès !</p>");

            // Test liste
            out.println("<h2>Liste des étudiants :</h2>");
            etudiantEmpl.getEtudiants().forEach(e -> {
                out.println("<p>Étudiant: " + e.getNom() + " " + e.getPrenom() + "</p>");
            });

        } catch (Exception e) {
            out.println("<p style='color: red'>❌ Erreur: " + e.getMessage() + "</p>");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}
