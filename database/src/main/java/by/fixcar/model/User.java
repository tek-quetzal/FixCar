package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

public abstract class User {
    private int id;
    private String name;



    User(int id,String name){setId(id); setName(name);}


    public int getId(){return id;}
    public String getName(){return name;}

    public void setId(int id){
        if(id<0) throw new ValidationException("Введен неправильный ID(должен быть больше нуля)");
        this.id = id;
    }

    public void setName(String name){
        if (name.isEmpty()) throw new ValidationException("Введен неправильный ID(должен быть больше нуля)");
        this.name = name;
    }




}
