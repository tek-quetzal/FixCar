package by.fixcar.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Client extends User {

    private String phone;

    public Client(int id, String name, String phone) {
        super(id, name);
        setPhone(phone);
    }


    public String getPhone() {
        return phone;
    }


    public void setPhone(String phone) {
        if(phone==null || phone.isBlank()) throw new IllegalArgumentException("Введён пустой номер телефона!");
        this.phone = phone;
    }
}
