package by.fixcar.db;
import by.fixcar.exceptions.DataAccessException;
import by.fixcar.model.Car;
import by.fixcar.model.Mechanic;
import by.fixcar.model.Order;
import by.fixcar.model.Status;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDAO implements DAO<Order>{
    private static final String SAVE_SQL = """
            INSERT INTO orders(description, cost,status,car_id, mechanic_id) VALUES(?,?,?,?,?)
            """;

    private static final String UPDATE_SQL = """
            UPDATE orders SET description=?, cost=?, status=?,car_id=?, mechanic_id=? WHERE id=?
            """;

    private static final String DELETE_SQL = """
            DELETE FROM orders WHERE id=?
            """;

    private static final String FIND_ALL_SQL = """
            SELECT o.id AS order_id, o.description, o.cost, o.status, o.car_id, o.mechanic_id,m.name AS mechanic_name, m.specialization,c.brand,
            c.model,c.year,c.license_plate,c.owner_id
            FROM orders o JOIN
            cars c ON o.car_id = c.id JOIN mechanics m ON o.mechanic_id = m.id
            """;

    private static final String FIND_BY_ID_SQL = FIND_ALL_SQL + " WHERE o.id=?";



    private OrderDAO(){}

    private static final OrderDAO INSTANCE = new OrderDAO();

    public static OrderDAO getInstance(){return INSTANCE;}



    @Override
    public Order save(Order order) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(SAVE_SQL,PreparedStatement.RETURN_GENERATED_KEYS)
        )
        {
            preparedStatement.setString(1,order.getDescription());
            preparedStatement.setBigDecimal(2,order.getCost());
            preparedStatement.setString(3,order.getStatus().name());
            preparedStatement.setInt(4,order.getCar().getId());
            preparedStatement.setInt(5,order.getMechanic().getId());

            preparedStatement.executeUpdate();

            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if(resultSet.next()){
                order.setId(resultSet.getInt(1));
            }

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при сохранении заказов в OrderDAO",e);
        }
        return order;
    }

    @Override
    public boolean delete(int id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL)
        )
        {
            preparedStatement.setInt(1,id);
            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при удалении заказов в OrderDAO",e);
        }
    }

    @Override
    public boolean update(Order order) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL)
        )
        {
            preparedStatement.setString(1,order.getDescription());
            preparedStatement.setBigDecimal(2,order.getCost());
            preparedStatement.setString(3,order.getStatus().name());
            preparedStatement.setInt(4,order.getCar().getId());
            preparedStatement.setInt(5,order.getMechanic().getId());
            preparedStatement.setInt(6,order.getId());

            return preparedStatement.executeUpdate()>0;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при обновлении заказов в OrderDAO",e);
        }
    }

    @Override
    public Optional<Order> findById(int id) {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID_SQL)
        )
        {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            Order order = null;
            if(resultSet.next()){
                order = buildOrder(resultSet);
            }
            return Optional.ofNullable(order);

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске заказов по ID в OrderDAO",e);
        }
    }


    @Override
    public List<Order> findAll() {
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL)
        )
        {
            List<Order> orders = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orders.add(buildOrder(resultSet));
            }

            return orders;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех заказов в OrderDAO",e);
        }
    }



    public List<Order> findByStatus(Status status){
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL + " WHERE o.status = ?")
        )
        {
            preparedStatement.setString(1,status.name());
            List<Order> orders = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orders.add(buildOrder(resultSet));
            }

            return orders;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех заказов по статусу в OrderDAO",e);
        }
    }




    public List<Order> findByCarId(int carId){
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL + " WHERE o.car_id = ?")
        )
        {
            preparedStatement.setInt(1,carId);
            List<Order> orders = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orders.add(buildOrder(resultSet));
            }

            return orders;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех заказов по ID машины в OrderDAO",e);
        }
    }


    public List<Order> findByMechanicId(int mechanicId){
        try(Connection connection = ConnectionManager.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_SQL + " WHERE o.mechanic_id = ?")
        )
        {
            preparedStatement.setInt(1,mechanicId);
            List<Order> orders = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                orders.add(buildOrder(resultSet));
            }

            return orders;

        } catch (SQLException e) {
            throw new DataAccessException("Ошибка при поиске всех заказов по механику в OrderDAO",e);
        }
    }

















    private Order buildOrder(ResultSet resultSet) throws SQLException{
        Car car = new Car(resultSet.getInt("car_id"),resultSet.getString("brand"),
                resultSet.getString("model"),resultSet.getInt("year"),
                resultSet.getString("license_plate"),resultSet.getInt("owner_id"));

        Mechanic mechanic = new Mechanic(resultSet.getInt("mechanic_id"),
                resultSet.getString("mechanic_name"),
                resultSet.getString("specialization"));

        return new Order(resultSet.getInt("order_id"),
                resultSet.getString("description"),
                resultSet.getBigDecimal("cost"),
                Status.valueOf(resultSet.getString("status")),
                car, mechanic);
    }




}
