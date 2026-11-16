package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

public class Mechanic extends User{

    private String specialization;

    public Mechanic(int id,String name, String specialization){
        super(id,name);
        setSpecialization(specialization);
    }


    public String getSpecialization(){return specialization;}

    public void setSpecialization(String specialization){
        if(specialization==null || specialization.isBlank() || specialization.length()>50)
            throw new ValidationException("Введено некорректное значение для специализации механика");
        this.specialization = specialization;
    }




}
