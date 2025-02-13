package fr.cdamassy2021.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import fr.cdamassy2021.model.Canal;
import fr.cdamassy2021.model.Personne;

/**
 *
 * @author borel,beatrice,huawei
 */
public class CanalDao {

    private static final String TOUS_LES_CANAUX_DU_MEMBRE
            = "SELECT nom, ca.id_canal\n"
            + "FROM membre_canal \n"
            + "            INNER JOIN canal ca\n"
            + "                    ON membre_canal.id_canal = ca.id_canal\n"
            + "WHERE id_personne=?\n"
            + "ORDER BY ca.id_canal";
    private static final String TOUS_LES_CANAUX
            = "SELECT * FROM canal ORDER BY id_canal";

    private static final String TOUS_LES_MEMBRE_DU_CANAL = ""
            + "SELECT id_canal,p.id_personne,p.nom,p.prenom, ajoute_a \n"
            + "FROM membre_canal mc\n"
            + "		INNER JOIN personne p\n"
            + "			ON mc.id_personne = p.id_personne\n"
            + "WHERE id_canal=?";

    private static final String ENLEVER_MEMBRE_DU_CANAL
            = "DELETE FROM membre_canal WHERE id_personne=? AND id_canal=?";

    private static final String AJOUTER_MEMBRE_AU_CANAL
            = "INSERT INTO cdamassy2021.membre_canal (id_personne,id_canal)\n"
            + "VALUES (?,?);";

    /**
     * Canal d'id et de nom donnés sur lesquels sont enregistres le membre en
     * session. Classe le resultat par id et renvoie null si pas trouvé
     *
     * @param id_personne
     * @return Une liste de Canal
     * @throws SQLException
     */
    public static List<Canal> getAllByIdPersonne(int id_personne) throws SQLException {

        List<Canal> canaux = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(TOUS_LES_CANAUX_DU_MEMBRE);
        stmt.setInt(1, id_personne);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            canaux.add(new Canal(
                    rs.getInt("id_canal"),
                    rs.getString("nom")
            ));
        }
        return canaux;
    }

    public static List<Canal> getAllCanaux() throws SQLException {

        // Mettre en dur le résultat
        List<Canal> canaux = new ArrayList<>();
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(TOUS_LES_CANAUX);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            canaux.add(new Canal(
                    rs.getInt("id_canal"),
                    rs.getString("nom")
            ));
        }
        return canaux;
    }

    /**
     * Liste des membres d'un canal donnés. renvoi l'ensemble des membres en
     * fonction de l'idCanal et renvoie null si pas trouvé
     *
     * @param idCanal
     * @return Une liste de Personne
     * @throws SQLException
     */
    public static List<Personne> getMembresDuCanal(int idCanal) throws SQLException {
        // TODO Auto-generated method stub
        List<Personne> membresCanal = new ArrayList<Personne>();
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(TOUS_LES_MEMBRE_DU_CANAL);
        stmt.setInt(1, idCanal);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            membresCanal.add(new Personne(
                    rs.getInt("id_personne"),
                    rs.getString("nom"),
                    rs.getString("prenom")
            ));
        }
        return membresCanal;
    }

    public static int SupprimerMembreDuCanal(int idPersonne, int idCanal) throws SQLException {
        // TODO Auto-generated method stub
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(ENLEVER_MEMBRE_DU_CANAL);

        stmt.setInt(1, idPersonne);
        stmt.setInt(2, idCanal);

        int row = stmt.executeUpdate();
        return row;
    }

    public static int AjouterMembreAuCanal(int idPersonne, int idCanal) throws SQLException {
        Connection connection = Database.getConnection();
        PreparedStatement stmt = connection.prepareStatement(AJOUTER_MEMBRE_AU_CANAL);

        stmt.setInt(1, idPersonne);
        stmt.setInt(2, idCanal);

        int row = stmt.executeUpdate();
        return row;
    }

}
