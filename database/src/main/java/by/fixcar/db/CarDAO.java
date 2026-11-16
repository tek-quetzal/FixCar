package by.fixcar.db;

import by.fixcar.exceptions.DataAccessException;
import by.fixcar.model.Car;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CarDAO implements DAO<Car> {

    private static final String SAVE_SQL = """
            INSERT INTO cars(brand,model,year,license_plate,owner_id) VALUES (?,?,?,?,?)
            """;

    private static final String DELETE_SQL = """
            DELETE FROM cars WHERE id=?
            """;

    private static final String UPDATE_SQL = """
            UPDATE cars SET brand=?, model=?, year=?,license_plate=?, owner_id=? WHERE id=?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT  id, brand, model, year, license_plate, owner_id FROM cars
            """;

    private static final String FIND_BY_ID_SQL =
            FIND_ALL_SQL + " WHERE id=?";


    private CarDAO(){}
    public static final CarDAO INSTANCE = new CarDAO();
    public static CarDAO getInstance(){return INSTANCE;}



    @Override
    public Car save(Car car) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL,PreparedStatement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.setString(1,car.getBrand());
            preparedStatement.setString(2,car.getModel());
            preparedStatement.setInt(3,car.getYear());
            preparedStatement.setString(4,car.getLicensePlate());
            preparedStatement.setInt(5,car.getOwnerId());
            preparedStatement.executeUpdate();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                car.setId(resultSet.getInt(1));
            }
        }

         catch (SQLException e) {
             throw new DataAccessException("Ошибка при сохранении данных в CarDAO",e);
        }

        return car;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL))
        {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при удалении данных в CarDAO",e);
        }

    }

    @Override
    public boolean update(Car car) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL))
        {
            preparedStatement.setString(1,car.getBrand());
            preparedStatement.setString(2,car.getModel());
            preparedStatement.setInt(3,car.getYear());
            preparedStatement.setString(4,car.getLicensePlate());
            preparedStatement.setInt(5,car.getOwnerId());
            preparedStatement.setInt(6,car.getId());

            return preparedStatement.executeUpdate() > 0;



        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при обновлении данных в CarDAO",e);
        }
    }


    @Override
    public Optional<Car> findById(int id) {
        try(Connection connection=ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL))
        {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Car car = null;
            if(resultSet.next()){
                car = buildCar(resultSet);
            }
            return Optional.ofNullable(car);
        }
        catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске по ID в CarDAO",e);
        }
    }


    public List<Car> findByOrderId(Integer ownerId) {
        try(Connection connection=ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL + "WHERE owner_id=?"))
        {
            preparedStatement.setInt(1,ownerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            if(resultSet.next()){
                cars.add(buildCar(resultSet));
            }
            return cars;
        }
        catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех автомобилей по ownerID в CarDAO",e);
        }
    }




    @Override
    public List<Car> findAll() {
        try(Connection connection=ConnectionManager.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Car> cars = new ArrayList<>();
            while(resultSet.next()){
                cars.add(buildCar(resultSet));
            }
            return cars;
        }
        catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех значений в CarDAO",e);
        }
    }


    private Car buildCar(ResultSet resultSet) throws SQLException {
        return  new Car (resultSet.getInt("id"),
                resultSet.getString("brand"),
                resultSet.getString("model"),
                resultSet.getInt("year"),
                resultSet.getString("license_plate"),
                resultSet.getInt("owner_id")
        );

    }



}