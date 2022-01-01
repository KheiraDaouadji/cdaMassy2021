/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fr.cdamassy2021.dao;

import fr.cdamassy2021.model.EFG;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author scdel
 */
public class EFGDao implements IDao<EFG> {

    protected static final String INSERT_EFG
        = "INSERT INTO `efg` (`intitule`, `id_createur`, `id_canal`) VALUES (?, ?, ?);";

    protected final static String SELECT_BY_ID
        = "SELECT * FROM efg WHERE id_efg = ?";
    
    
    @Override
    public boolean insert(EFG inserted) throws SQLException {
        Boolean result = false;
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_EFG,
            Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, inserted.getIntitule());
        statement.setInt(2, inserted.getIdCreateur());
        statement.setInt(3, inserted.getIdCanal());
                statement.execute();
        ResultSet res = statement.getGeneratedKeys();
        if (res.next()) {
            inserted.setId(res.getInt(1));
        }
        result = true;
        return result;
    }

    @Override
    public void delete(EFG deleted) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public EFG findById(long id) throws SQLException {
        EFG result = new EFG();
        Connection connection = Database.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_EFG);
        statement.setLong(1, id);
        boolean executeOk = statement.execute();
        ResultSet EFGSet = statement.getResultSet();
        if(executeOk){
            while(EFGSet.next()){
                result.setId(EFGSet.getInt("id_efg"));
                result.setIdCanal(EFGSet.getInt("id_canal"));
                result.setIdCreateur(EFGSet.getInt("id_createur"));
                result.setIntitule(EFGSet.getString("intitule"));
            }
        }
        
        // faire la gestion des groupes de l'exo
        return result;
    }

    @Override
    public ArrayList<EFG> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ArrayList<EFG> getAllPaging(int noPage, int nbElementsParPage) throws
        SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
