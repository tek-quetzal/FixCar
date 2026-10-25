package by.fixcar.service.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import by.fixcar.service.exceptions.ClientNotFoundException;
import by.fixcar.service.exceptions.MechanicNotFoundException;
import by.fixcar.service.exceptions.OrderNotFoundException;
import by.fixcar.model.*;

public class AutoService {
    private List<Client> clients = new ArrayList<>();
    private List<Mechanic> mechanics = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();



    public void addClient(Client client){
        clients.add(client);
    }

    public void addMechanic(Mechanic mechanic){
        mechanics.add(mechanic);
    }


    public Mechanic findMechanicById(int id){
        for(Mechanic mechanic : mechanics){
            if (mechanic.getId() == id){return mechanic;}
        }
        throw new MechanicNotFoundException("Ошибка! Механика с id: \" + id + \" не найдено!");
    }


    public Client findClientById(int id){
        for(Client client : clients){
            if (client.getId() == id) {return client;}
        }
        throw new ClientNotFoundException("Ошибка! Клиента с id: " + id + " не найдено!");
    }


    public Order findOrderById(int id){
        for(Order order : orders){
            if (order.getId() == id) {return order;}
        }
        throw new OrderNotFoundException("Ошибка! Заказа с id: " + id + " не найдено!");
    }


    public void addOrder(int id, String description, BigDecimal cost, Status status, Car car, Mechanic mechanic){
        orders.add(new Order(id, description, cost, status,car,mechanic));

    }


    public void addCarToClient(int idClient,Car car){
        findClientById(idClient).addCar(car);
    }


    public List<Car> findCarsByOwnerId(int idClient){
        return findClientById(idClient).getClientCars();
    }

    public List<Order> findOrdersByStatus(Status status){
        List<Order> ordersWithCurrentStatus = new ArrayList<>();

        for(Order order: orders) {
            if (order.getStatus()==status) ordersWithCurrentStatus.add(order);
        }
        return ordersWithCurrentStatus;
    }







}
