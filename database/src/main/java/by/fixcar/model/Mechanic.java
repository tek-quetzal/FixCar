package by.fixcar.model;

public class Mechanic extends User{

    private String specialization;

    public Mechanic(int id,String name, String specialization){
        super(id,name);
        this.specialization = specialization;
    }


    public String getSpecialization(){return specialization;}

    public void setSpecialization(String specialization){this.specialization = specialization;}




}
