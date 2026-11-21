package by.fixcar.service.services;

import by.fixcar.db.MechanicDAO;
import by.fixcar.db.OrderDAO;
import by.fixcar.exceptions.ValidationException;
import by.fixcar.model.Mechanic;
import by.fixcar.service.exceptions.NotFoundException;

import java.util.List;

public class MechanicService {
    private final MechanicDAO mechanicDAO = MechanicDAO.getInstance();
    private final OrderDAO orderDAO = OrderDAO.getInstance();


    public List<Mechanic> getAll(){
        return mechanicDAO.findAll();
    }

    public Mechanic getById(int id){
        return mechanicDAO.findById(id).orElseThrow(()-> new NotFoundException("Механик",id));
    }

    public Mechanic create(String name,String specialization){
        return mechanicDAO.save(new Mechanic(0,name,specialization));
    }

    public void update(int id, String name,String specialization){
        Mechanic mechanic = getById(id);
        mechanic.setName(name);
        mechanic.setSpecialization(specialization);
        mechanicDAO.update(mechanic);

    }


    public void delete(int id){
        getById(id);
        if(!orderDAO.findByMechanicId(id).isEmpty())  throw new ValidationException("Нельзя удалить механика,пока он есть в заказах");
        mechanicDAO.delete(id);
    }









}
