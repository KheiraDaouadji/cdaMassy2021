/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cdamassy2021.controller;

import fr.cdamassy2021.dao.PersonneDao;
import fr.cdamassy2021.model.Personne;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrateur
 */
@WebServlet(name = "InscriptionServlet", urlPatterns = {"/inscription"})
public class InscriptionServlet extends HttpServlet {

    private final String VUE = "WEB-INF/inscription.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("WEB-INF/inscription.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String vue = VUE;
        boolean valideForm = true;
        String prenom = request.getParameter("prenom");
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String pwd = request.getParameter("pwd");
        String tel = request.getParameter("tel");

        boolean valide = true;

        //test de validation du formulaire
        // compilation de la regex
        Pattern regexTel = Pattern.compile("0[0-9]{9}");
        // création d'un moteur de recherche
        Matcher matcherTel = regexTel.matcher(tel);
        // lancement de la recherche de toutes les occurrences
        boolean booleanTel = matcherTel.matches();
        if (tel.equals("")) {
            booleanTel = true;
        }

        Pattern regexEmail = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcherEmail = regexEmail.matcher(email);
        boolean booleanEmail = matcherEmail.matches();

        Pattern regexChampsVide = Pattern.compile("^ *$");

        Matcher matcherPrenom = regexChampsVide.matcher(prenom);
        boolean booleanPrenom = matcherPrenom.matches();

        Matcher matcherNom = regexChampsVide.matcher(nom);
        boolean booleanNom = matcherNom.matches();

        Matcher matcherMdp = regexChampsVide.matcher(pwd);
        boolean booleanMdp = matcherMdp.matches();

        if (!booleanEmail) {
            request.setAttribute("erreur_email", "Votre email est invalide");
            valide = false;
        }
        if (!booleanTel) {
            request.setAttribute("erreur_tel", "Votre téléphone est invalide");
            valide = false;
        }
        if (booleanPrenom) {
            request.setAttribute("erreur_prenom", "Veuillez remplir le champs prénom correctement");
            valide = false;
        }
        if (booleanNom) {
            request.setAttribute("erreur_nom", "Veuillez remplir le champs nom correctement");
            valide = false;
        }
        if (booleanMdp) {
            request.setAttribute("erreur_mdp", "Veuillez remplir le champs mot de passe correctement");
            valide = false;
        }

        if (valide) {
            PersonneDao dao = new PersonneDao();

            Personne p1 = new Personne(prenom, nom, email, tel, pwd);
            try {
                dao.insert(p1);

            } catch (SQLException ex) {
                if (ex.getErrorCode() == 1062) {
                    request.setAttribute("message", "Cet email existe déjà !");
                    valideForm = false;
                } else {
                    request.setAttribute("message", "Problème interne !");
                    valideForm = false;
                }
                Logger.getLogger(InscriptionServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        // redirection
        if (valideForm && valide) {
            request.setAttribute("messageSuccess", "Votre inscription est validé ! Vous pouvez vous maintenant vous connecter en cliquant sur connexion.");
        }

        request.getRequestDispatcher(vue).forward(request, response);

    }
}
