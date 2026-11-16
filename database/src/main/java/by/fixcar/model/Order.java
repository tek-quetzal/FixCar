package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

import java.math.BigDecimal;
import java.math.RoundingMode;

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





    public void setId(int id)
    {
        if(id<0) throw new ValidationException("Введен неправильный ID заказа(должен быть больше или равен нулю)");
        this.id = id;
    }

    public void setDescription(String description) {
        if(description==null || description.isBlank() || description.length() > 500)
            throw new ValidationException("Введено неверное описание заказа(должно быть не пустым и длиной <=500)");
        this.description = description;
    }

    public void setCost(BigDecimal cost) {
        if (cost == null || cost.compareTo(BigDecimal.ZERO)<0)
            throw new ValidationException("Введено неверное значение для цены заказа");
        BigDecimal normalCost = cost.setScale(2, RoundingMode.HALF_UP);

        if (normalCost.precision()>10) {
            throw new ValidationException("Стоимость заказа слишком велика");}

        this.cost = normalCost;
    }

    public void setCar(Car car) {
        if(car==null){throw new ValidationException("Вместо машины введено null");}
        this.car = car;
    }

    public void setMechanic(Mechanic mechanic) {
        if(mechanic==null){throw new ValidationException("Вместо механика введено null");}
        this.mechanic = mechanic;
    }



    public Order(int id, String description, BigDecimal cost, Status status, Car car, Mechanic mechanic) {
        setId(id);
        setDescription(description);
        setCost(cost);
        if (status == null) throw new ValidationException("Статус заказа не может быть null");
        this.status = status;
        setCar(car);
        setMechanic(mechanic);
    }

    public Order(int id, String description, BigDecimal cost, Car car, Mechanic mechanic) {
        this(id, description, cost, Status.IN_PROGRESS, car, mechanic);
    }




    public void changeStatus(Status newStatus) {
        if (newStatus == null) throw new ValidationException("Статус не может быть null");
        if (status != Status.IN_PROGRESS || newStatus == Status.IN_PROGRESS)
            throw new ValidationException("Нельзя сменить статус " + status.getRuStatus() + " на " + newStatus.getRuStatus() + "\"");
        this.status = newStatus;
    }




}
