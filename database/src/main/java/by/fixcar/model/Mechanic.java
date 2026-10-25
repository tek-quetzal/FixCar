package by.fixcar.model;

public class Mechanic extends User implements Displayable {

    private String specialization;

    public Mechanic(int id,String name, String specialization){
        super(id,name);
        this.specialization = specialization;
    }


    public String getSpecialization(){return specialization;}

    public void setSpecialization(String specialization){this.specialization = specialization;}


    @Override
    public void showInfo() {

        System.out.printf("ID механика: %d\nИмя механика: %s\nСпециализация: %s\n",getId(),getName(),specialization);
    }



}
