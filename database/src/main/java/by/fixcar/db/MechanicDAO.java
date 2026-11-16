package by.fixcar.db;

import by.fixcar.exceptions.DataAccessException;
import by.fixcar.model.Mechanic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MechanicDAO implements DAO<Mechanic>{

    private final static String SAVE_SQL =  """
            INSERT INTO mechanics(name,specialization)
            VALUES(?,?)
            """;


    private final static String DELETE_SQL  = """
            DELETE FROM mechanics WHERE id=?
            """;


    private final static String UPDATE_SQL  = """
            UPDATE mechanics SET name = ?, specialization = ? WHERE id = ?
            """;


    private final static String FIND_ALL_SQL  = """
            SELECT id,name,specialization FROM mechanics
            """;


    private final static String FIND_BY_ID_SQL  = FIND_ALL_SQL + """
             WHERE id = ?
            """;


    private MechanicDAO(){}
    private final static MechanicDAO INSTANCE = new MechanicDAO();
    public static  MechanicDAO getInstance(){
        return INSTANCE;
    }


    private Mechanic buildMechanic(ResultSet resultSet) throws SQLException{
        return new Mechanic(resultSet.getInt("id"),
                resultSet.getString("name"),
                resultSet.getString("specialization"));
    }




    @Override
    public Mechanic save(Mechanic mechanic) {
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL,PreparedStatement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1,mechanic.getName());
            preparedStatement.setString(2,mechanic.getSpecialization());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){mechanic.setId(resultSet.getInt(1));}
            return mechanic;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при сохранении данных в MechanicDAO",e);
        }
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL))
        {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при удалении данных в MechanicDAO",e);
        }


    }

    @Override
    public boolean update(Mechanic mechanic) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL))
        {
            preparedStatement.setString(1,mechanic.getName());
            preparedStatement.setString(2,mechanic.getSpecialization());
            preparedStatement.setInt(3,mechanic.getId());
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при обновлении данных в MechanicDAO",e);
        }
    }

    @Override
    public Optional<Mechanic> findById(int id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL))
        {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Mechanic mechanic = null;
            if(resultSet.next()){mechanic = buildMechanic(resultSet);}
            return Optional.ofNullable(mechanic);
        }

        catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске по ID в MechanicDAO",e);
        }
    }

    @Override
    public List<Mechanic> findAll() {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Mechanic> mechanics = new ArrayList<>();
            while(resultSet.next()){
                mechanics.add(buildMechanic(resultSet));
            }
            return mechanics;
        }

        catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех значений в MechanicDAO",e);
        }
    }







}
