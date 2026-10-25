package by.fixcar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client extends User implements Displayable {

    private String phone;
    private List<Car> clientCars = new ArrayList<>();

    public Client(int id, String name, String phone) {
        super(id, name);
        setPhone(phone);
    }


    public String getPhone() {
        return phone;
    }

    public List<Car> getClientCars() {
        return Collections.unmodifiableList(clientCars);
    }


    public void setPhone(String phone) {
        if(phone.isEmpty()) throw new IllegalArgumentException("Введён пустой номер телефона!");
        this.phone = phone;
    }

    public void setClientCars(List<Car> clientCars)
    {
        for (Car car : clientCars){
            car.setOwner(this);
        }
        this.clientCars = clientCars;
    }


    public void addCar(Car car)
    {
        if(car == null) throw new IllegalArgumentException("Передается null,а ожидалась машина");
        clientCars.add(car);
        car.setOwner(this);

    }


    public void showAllCars() {
        System.out.println("Все авто клиента: ");
        if (clientCars.isEmpty()) System.out.println("Пока что у данного клиента нет машин\n");
        else for (Car car : clientCars) car.showInfo();
    }

    @Override
    public void showInfo() {
        System.out.printf("ID клиента: %d\nИмя клиента: %s\nНомер телефона: %s\n",getId(),getName(),getPhone());
        showAllCars();
    }










    }