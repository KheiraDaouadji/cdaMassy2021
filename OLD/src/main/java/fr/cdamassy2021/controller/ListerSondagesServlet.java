/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.DaoFactory;
import fr.cdamassy2021.dao.QuestionDao;
import fr.cdamassy2021.model.Personne;
import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Sondage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author thoma
 */
@WebServlet(name = "ListerSondages", urlPatterns = {"/listesondages"})
public class ListerSondagesServlet extends HttpServlet {

    private final String VUE_OK = "WEB-INF/sondages.jsp";
    private final String VUE_ERREUR = "WEB-INF/erreur.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        QuestionDao dao = DaoFactory.getInstance().getQuestionDao();
        String vue = VUE_OK;
        try {
            final HttpServletRequest req = (HttpServletRequest) request;
            final HttpSession session = req.getSession();
            // Les paramètres encore en dur
            ArrayList<Sondage> sondages = dao.getAllSondagesPaging(1, 10);
            // Mettre en post-it les questions
            request.setAttribute("sondages", sondages);
        } catch (SQLException exc) {
            vue = VUE_ERREUR;
            request.setAttribute("message", "Pb avec la BD");

            // Journaliser l'exception dans le log de tomcat
            exc.printStackTrace();
        }
        // Passer la main à la vue
        request.getRequestDispatcher(vue).forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
