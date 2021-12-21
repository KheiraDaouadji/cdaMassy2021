/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;
import fr.cdamassy2021.model.Question;
import fr.cdamassy2021.model.Proposition;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * class QuestionDao<br>
 * 
 * eng: This Dao is responsible for inserting and retrieving 'Question' beans from the DB
 * and initialise those with their respective Proposition beans list.<br>
 * For this reason it also provide those operations with the 'Proposition' beans.<br>
 * <br>
 * fr: Cette Dao a pour résponsabilité d'insérer dans la BDD, et d'en extraire<br>
 * les beans 'Question' initialisés avec leur liste de Propositions respectives.<br>
 * Pour ce faire, elle assure aussi ces opérations avec avec les beans <br>
 * 'Proposition'.
 *
 * @author Kamal, Thomas
 */
public interface QuestionDao extends Dao<Question> {

    
        /**
         * Insere newQuestion dans la base de donnée et positionne newQuestion.id à
         * la valeur auto-incrémentée dans la base.
         *
         * @param newQuestion est le bean à inserer.
         * @return isInsertionOk est le résultat de l'opération
         * @throws SQLException
         */
        @Override
        public boolean insert(Question newQuestion) 
                throws SQLException;

        /**
         * Insertion des 'Question' et de sa liste de 'Proposition' sur le modèle
         * transactionnel.<br>
         * Si l'insertion de la question ou de chacune des proposition de la <br>
         * liste échoue la transaction est annulée avec un connection.rollback()<br>
         * <br>
         *
         * @param inserted : question à inserer dans la DB<br>
         * @param propositions : reponses possibles relatives à la question<br>
         * @return isCommit: true si l'insertion s'est passée comme prévu.<br>
         * @throws SQLException <br>
         */
        public boolean insert(Question inserted, List<Proposition> propositions)
                throws SQLException;

        /**
         * Renvoit la 'Question' qui a pour id searchedId, initialisé avec la liste
         * complête des 'Propositions' de réponses qui lui sont associées.
         *
         * @param searchedId est la clef à trouver dans la base de données.
         * @return le bean 'Question' recherché.
         * @throws SQLException
         */
        @Override
        public Question findById(long searchedId) 
                throws SQLException;

        /**
         * Renvoit une liste de toutes les 'Question' en paginant à raison de
         * nbElementsParPages par pages pour la page n° noPage.
         *
         * Chaque 'Question' est initialisée avec la liste de 'Propositions' de
         * réponses qui lui est associée.
         *
         * @param noPage n° de la page à afficher (1ere = 1)
         * @param nbElementsParPage nombre maximal de questions à retourner
         * @return
         * @throws SQLException
         *
         */
        public ArrayList<Question> getAllPaging(int noPage, 
                int nbElementsParPage) 
                throws SQLException;
        
        /**
         * Renvoit une liste de toutes les 'Question' appartenant au canal
         * designé par idCanal en paginant à raison de
         * nbElementsParPages par pages pour la page n° noPage.
         *
         * Chaque 'Question' est initialisée avec la liste de 'Propositions' de
         * réponses qui lui est associée.
         *
         * @param noPage n° de la page à afficher (1ere = 1)
         * @param nbElementsParPage nombre maximal de questions à retourner
         * @return
         * @throws SQLException
         *
         */
        public ArrayList<Question> getAllByCanalPaging(int idCanal, int noPage,
                int nbElementsParPage) throws SQLException;
        
        @Override
        public void delete(Question deleted) throws SQLException;

        @Override
        public ArrayList<Question> findAll() throws SQLException;
}
