package by.fixcar.service.services;

import by.fixcar.db.CarDAO;
import by.fixcar.db.OrderDAO;
import by.fixcar.exceptions.ValidationException;
import by.fixcar.model.Car;
import by.fixcar.service.exceptions.DuplicateEntityException;
import by.fixcar.service.exceptions.NotFoundException;

import java.util.List;

public class CarService {
    private final OrderDAO orderDAO = OrderDAO.getInstance();
    private final CarDAO carDAO = CarDAO.getInstance();
    private final ClientService clientService = new ClientService();




    public Car getById(int id){
        return carDAO.findById(id).orElseThrow(()->new NotFoundException("Машина",id));
    }

    public List<Car> getByOwnerId(int id){
        clientService.getById(id);
        return carDAO.findByOwnerId(id);
    }

    public List<Car> getAll(){
        return carDAO.findAll();
    }

    public Car create(String brand,String model,int year,int ownerId,String licensePlate){
        clientService.getById(ownerId);
        checkLicensePlateIsFree(licensePlate,0);
        return carDAO.save(new Car(0,brand,model,year,licensePlate,ownerId));
    }


    public void update(int id,String brand,String model,int year,String licensePlate,int ownerId){
        Car existCar = getById(id);
        clientService.getById(ownerId);

        checkLicensePlateIsFree(licensePlate,id);

        existCar.setBrand(brand);
        existCar.setModel(model);
        existCar.setYear(year);
        existCar.setLicensePlate(licensePlate);
        existCar.setOwnerId(ownerId);

        carDAO.update(existCar);
    }


    private void checkLicensePlateIsFree(String licensePlate, int id){
        carDAO.findByLicensePlate(licensePlate).filter(car-> car.getId() != id).
                ifPresent(c->{throw new DuplicateEntityException("Госномер " + licensePlate + " уже зарегистрирован");});
    }



    public void delete(int id){
        getById(id);
        if (!orderDAO.findByCarId(id).isEmpty()) throw new ValidationException("Нельзя удалить машину,для которой есть заказы!");
        carDAO.delete(id);
    }

}
