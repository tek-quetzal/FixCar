package by.fixcar.service.services;

import by.fixcar.db.CarDAO;
import by.fixcar.db.ClientDAO;
import by.fixcar.exceptions.ValidationException;
import by.fixcar.model.Client;
import by.fixcar.service.exceptions.NotFoundException;

import java.util.List;


public class ClientService {
    private final ClientDAO clientDAO = ClientDAO.getInstance();
    private final CarDAO carDAO = CarDAO.getInstance();


    public Client create(String name, String phone){
        return clientDAO.save(new Client(0,name,phone));
    }


    public List<Client> getAll(){
        return clientDAO.findAll();
    }



    public Client getById(int id){
        return clientDAO.findById(id).orElseThrow(()->new NotFoundException("Клиент", id));
    }



    public void update(int id,String name,String phone){
        Client client = getById(id);
        client.setName(name);
        client.setPhone(phone);
        clientDAO.update(client);
    }



    public void delete(int id){
        getById(id);
        if (!carDAO.findByOwnerId(id).isEmpty()) throw new ValidationException("Нельзя удалить клиента,пока у него есть машины");
        clientDAO.delete(id);
    }


}
