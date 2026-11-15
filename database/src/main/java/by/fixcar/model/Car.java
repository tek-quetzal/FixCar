package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

import java.time.Year;

public class Car {
    private int id;
    private String brand;
    private String model;
    private int year;
    private int ownerId;
    private String licensePlate;



    public Car(int id,String brand,String model,int year,String licensePlate){
        setId(id);
        setBrand(brand);
        setModel(model);
        setYear(year);
        setLicensePlate(licensePlate);
    }


    public Car(int id,String brand,String model,int year,String licensePlate,int ownerId){
        this(id,brand,model,year,licensePlate);
        setOwner(ownerId);
    }


    public String getLicensePlate(){return licensePlate;}
    public int getId(){return id;}
    public String getBrand(){return brand;}
    public String getModel(){return model;}
    public int getYear(){return year;}
    public int getOwnerId(){return ownerId;}


    public void setLicensePlate(String licensePlate){
        if(licensePlate==null || licensePlate.length()>20 || licensePlate.isBlank()) throw new ValidationException("Введен неправильный ID(должен быть больше нуля)");
        this.licensePlate = licensePlate;
    }
    public void setId(int id){
        if(id<0) throw new ValidationException("Введен неправильный ID(должен быть больше нуля)");
        this.id = id;
    }
    public void setBrand(String brand) {
        if(brand.isEmpty()) throw new ValidationException("Введён пустой бренд!");
        this.brand = brand;
    }

    public void setModel(String model) {
        if(model.isBlank()) throw new ValidationException("Введена пустая модель!");
        this.model = model;
    }
    public void setYear(int year){
        if (1950>year || year > Year.now().getValue()) throw new ValidationException("Введен неверный год выпуска машины(нужен от 1950 до " + Year.now().getValue()+ ")");
        this.year = year;
    }


    public void setOwner(int ownerId){
        if(ownerId<0){throw new ValidationException("Введено неверное ID владельца для авто");}
        this.ownerId= ownerId;}






}
