package by.fixcar.service.services;

import by.fixcar.db.OrderDAO;
import by.fixcar.exceptions.ValidationException;
import by.fixcar.model.Car;
import by.fixcar.model.Mechanic;
import by.fixcar.model.Order;
import by.fixcar.model.Status;
import by.fixcar.service.exceptions.NotFoundException;

import java.math.BigDecimal;
import java.util.List;


public class OrderService {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final MechanicService mechanicService = new MechanicService();
    private final CarService carService = new CarService();


    public List<Order> getByStatus(Status status){
        return orderDAO.findByStatus(status);
    }

    public List<Order> getByMechanic(int mechanicId){
        mechanicService.getById(mechanicId);
        return orderDAO.findByMechanicId(mechanicId);
    }

    public List<Order> getByCar(int carId){
        carService.getById(carId);
        return orderDAO.findByCarId(carId);
    }


    public Order getById(int id){
        return orderDAO.findById(id).orElseThrow(()->new NotFoundException("Заказ",id));
    }


    public List<Order> getAll(){
        return orderDAO.findAll();
    }

    public Order create(String description, BigDecimal cost,int carId,int mechanicId){
        Car existCar = carService.getById(carId);
        Mechanic existMechanic = mechanicService.getById(mechanicId);
        return orderDAO.save(new Order(0,description,cost,existCar,existMechanic));

    }


    public void changeStatus(int id,Status status){
        Order orderForUpdateStatus = getById(id);
        orderForUpdateStatus.changeStatus(status);
        orderDAO.update(orderForUpdateStatus);
    }


    public void update(int id,String description, BigDecimal cost, Status status,int mechanicId){
        Order orderForUpdate = getById(id);
        if(orderForUpdate.getStatus() == Status.CANCELLED || orderForUpdate.getStatus() == Status.COMPLETED)
            throw new ValidationException("Нельзя изменить заказ со статусом завершён или отменён");

        orderForUpdate.setDescription(description);
        orderForUpdate.setCost(cost);

        if(status!=orderForUpdate.getStatus()){orderForUpdate.changeStatus(status);}

        orderForUpdate.setMechanic(mechanicService.getById(mechanicId));

        orderDAO.update(orderForUpdate);

    }

    public void delete(int id){
        getById(id);
        orderDAO.delete(id);
    }


}
