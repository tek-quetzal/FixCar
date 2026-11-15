package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

import java.math.BigDecimal;

public class Order {
    private int id;
    private String description;
    private BigDecimal cost;
    private Status status;
    private Car car;
    private Mechanic mechanic;


    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public Status getStatus() {
        return status;
    }

    public Car getCar() {
        return car;
    }

    public Mechanic getMechanic() {
        return mechanic;
    }





    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    private void setStatus(Status status) {
        this.status = status;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void setMechanic(Mechanic mechanic) {
        this.mechanic = mechanic;
    }



    public Order(int id, String description, BigDecimal cost, Status status, Car car, Mechanic mechanic) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.status = status;
        if(car==null || mechanic==null) throw new ValidationException("В конструктор заказа был передан объект равный null");
        this.car = car;
        this.mechanic = mechanic;
    }


    public Order(int id, String description, BigDecimal cost, Car car, Mechanic mechanic) {
        this.id = id;
        this.description = description;
        this.cost = cost;
        this.status = Status.IN_PROGRESS;
        this.car = car;
        this.mechanic = mechanic;
    }



    public void changeStatus(Status newStatus) {
        if (newStatus == null) throw new ValidationException("Статус не может быть null");
        if (status != Status.IN_PROGRESS || newStatus == Status.IN_PROGRESS)
            throw new ValidationException("Нельзя сменить статус " + status.getRuStatus() + " на " + newStatus.getRuStatus() + "\"");
        this.status = newStatus;
    }




}
