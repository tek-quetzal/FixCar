package by.fixcar.db;

import by.fixcar.model.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import by.fixcar.exceptions.DataAccessException;


public class ClientDAO implements DAO<Client>{

    private final static ClientDAO INSTANCE = new ClientDAO();


    private final static String SAVE_SQL =  """
            INSERT INTO clients(name,phone)
            VALUES(?,?)
            """;


    private final static String DELETE_SQL  = """
            DELETE FROM clients WHERE id=?
            """;


    private final static String UPDATE_SQL  = """
            UPDATE clients SET name = ?, phone = ? WHERE id = ?
            """;


    private final static String FIND_ALL_SQL  = """
            SELECT id,name,phone FROM clients
            """;


    private final static String FIND_BY_ID_SQL  = FIND_ALL_SQL + """
             WHERE id = ?
            """;



    @Override
    public Client save(Client client){
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL, PreparedStatement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1,client.getName());
            preparedStatement.setString(2,client.getPhone());
            preparedStatement.executeUpdate();
            ResultSet idResSet = preparedStatement.getGeneratedKeys();
            if (idResSet.next()){
                client.setId(idResSet.getInt(1));
            }
        }
        catch(SQLException e){
            throw new DataAccessException("Ошибка при сохранении данных в ClientDAO",e);
        }

        return client;

    }


    @Override
    public  boolean delete(int id){
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL))
        {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;
        }
        catch(SQLException e){
            throw new DataAccessException("Ошибка при удалении данных в ClientDAO",e);
        }

    }


    @Override
    public boolean update(Client client) {
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)){
            preparedStatement.setString(1,client.getName());
            preparedStatement.setString(2,client.getPhone());
            preparedStatement.setInt(3,client.getId());
            return preparedStatement.executeUpdate()>0;
        }

        catch(SQLException e){
            throw new DataAccessException("Ошибка при обновлении данных в ClientDAO",e);
        }
    }



    @Override
    public Optional<Client> findById(int id) {
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL))
        {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Client client = null;
            if (resultSet.next()){client = buildClient(resultSet);}
            return Optional.ofNullable(client);

        }
        catch(SQLException e){
            throw new DataAccessException("Ошибка при поиске по ID в ClientDAO",e);
        }
    }


    @Override
    public List<Client> findAll() {
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Client> clients = new ArrayList<>();
            while(resultSet.next()){
                clients.add(buildClient(resultSet));
            }
            return clients;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех значений в ClientDAO",e);
        }


    }





    protected static Client buildClient(ResultSet resultSet) throws SQLException {
        return  new Client (resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("phone"));
    }



    public static ClientDAO getInstance(){
        return INSTANCE;
    }


    private ClientDAO(){}

}






