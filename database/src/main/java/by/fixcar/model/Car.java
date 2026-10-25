package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

import java.time.Year;

public class Car implements Displayable {
    private int id;
    private String brand;
    private String model;
    private int year;
    private Client owner;
    private String licensePlate;



    public Car(int id,String brand,String model,int year){
        setId(id);
        setBrand(brand);
        setModel(model);
        setYear(year);
    }


    public Car(int id,String brand,String model,int year,Client owner){
        this(id,brand,model,year);
        setOwner(owner);
    }



    public int getId(){return id;}
    public String getBrand(){return brand;}
    public String getModel(){return model;}
    public int getYear(){return year;}
    public Client getOwner(){return owner;}


    public void setId(int id){
        if(id<0) throw new ValidationException("Введен неправильный ID(должен быть больше нуля)");
        this.id = id;
    }
    public void setBrand(String brand) {
        if(brand.isEmpty()) throw new ValidationException("Введён пустой бренд!");
        this.brand = brand;
    }

    public void setModel(String model) {
        if(model.isEmpty()) throw new ValidationException("Введена пустая модель!");
        this.model = model;
    }
    public void setYear(int year){
        if (1950>year || year > Year.now().getValue()) throw new ValidationException("Введен неверный год выпуска машины(нужен от 1950 до " + Year.now().getValue()+ ")");
        this.year = year;
    }


    public void setOwner(Client owner){
        if(owner==null){throw new ValidationException("Введено для создания авто значение null вместо владельца!");}
        this.owner= owner;}

    @Override
    public void showInfo(){
        System.out.printf("Машина с id:%d\nБренд: %s\nМодель: %s\nГод выпуска: %d\n\n",
                id, brand, model, year);
    }




}
