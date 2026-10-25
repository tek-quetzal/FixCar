package by.fixcar.service.console;
import java.math.BigDecimal;
import java.util.List;

import by.fixcar.service.exceptions.ClientNotFoundException;
import by.fixcar.model.*;
import by.fixcar.service.services.AutoService;

public class Main{
    public static void main(String[] args){
        Client ivan  = new Client(1, "Иван Петров", "+7 900 111-22-33");
        Client anna  = new Client(2, "Anna Smith", "+49 170 5551234");
        Client maria = new Client(3, "Мария Сидорова", "+7 911 000-00-00");


        Car saab   = new Car(10, "Saab", "9-3", 2005);
        Car audi   = new Car(11, "Audi", "A4", 2010);
        Car toyota = new Car(12, "Toyota", "Corolla", 2018);


        Mechanic sergey = new Mechanic(101, "Сергей Козлов", "двигатель");
        Mechanic hans   = new Mechanic(102, "Hans Mueller", "электрика");
        Mechanic oleg   = new Mechanic(103, "Олег Новиков", "ходовая часть");

        AutoService service = new AutoService();
        service.addMechanic(sergey);
        service.addMechanic(hans);
        service.addMechanic(oleg);

        service.addClient(ivan);
        service.addClient(anna);
        service.addClient(maria);

        service.addCarToClient(1, saab);
        service.addCarToClient(1, audi);
        service.addCarToClient(2, toyota);

        service.addOrder(1, "Замена масла",             new BigDecimal("150"),  Status.IN_PROGRESS, saab,   sergey);
        service.addOrder(2, "Замена тормозных колодок", new BigDecimal("60"), Status.IN_PROGRESS,  saab,   oleg);
        service.addOrder(3, "Диагностика электрики",    new BigDecimal("40"),  Status.IN_PROGRESS, audi,   hans);
        service.addOrder(4, "Плановое ТО",              new BigDecimal("70"), Status.COMPLETED,  toyota, sergey);

        System.out.println(ivan.getClientCars().size());
        System.out.println(anna.getClientCars().size());
        System.out.println(maria.getClientCars().size());
        System.out.println(saab.getOwner() == ivan);


        for (int badId : new int[]{99, -1, 0}) {
            try {
                service.addCarToClient(badId, new Car(13, "BMW", "X5", 2020));
            } catch (ClientNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }


        service.findCarsByOwnerId(1);
        service.findCarsByOwnerId(3);





        List<Order> inPrepare = service.findOrdersByStatus(Status.IN_PROGRESS);

        try{
            inPrepare.getFirst().changeStatus(Status.CANCELLED);
        }

        catch(IllegalArgumentException e){
            System.out.println(e.getMessage());
        }

        service.findOrdersByStatus(Status.COMPLETED);
        ConsolePrinter.printAll(service.findOrdersByStatus(Status.CANCELLED));

    }
}