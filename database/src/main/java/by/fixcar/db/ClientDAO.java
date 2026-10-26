package by.fixcar.db;

import by.fixcar.model.Client;
import by.fixcar.db.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ClientDAO implements DAO<Client>{

    private final static ClientDAO INSTANCE = new ClientDAO();

    private final static String SAVE =  """
            INSERT INTO clients(name,phone)
            VALUES(?,?);
            """;


    private final static String DELETE = """
            DELETE FROM clients WHERE id=?;
            """;




    @Override
    public Client save(Client client){
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preStatement = connection.prepareStatement(SAVE, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            preStatement.setString(1,client.getName());
            preStatement.setString(2,client.getPhone());
            preStatement.executeUpdate();
            ResultSet idResSet = preStatement.getGeneratedKeys();
            if (idResSet.next()){
                client.setId(idResSet.getInt(1));
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }

        return client;

    }

    @Override
    public  boolean delete(Integer id){
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preStatement = connection.prepareStatement(DELETE))
        {
            preStatement.setInt(1,id);
            return preStatement.executeUpdate()>0;
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }

    }

















    @Override
    public boolean update(Client obj) {
        return false;
    }

    @Override
    public Client findById(Integer id) {
        return null;
    }

    @Override
    public List<Client> findAll() {
        return List.of();
    }


    public static ClientDAO getInstance(){
        return INSTANCE;
    }


    private ClientDAO(){}












}






