package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

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
        if(phone==null || phone.isBlank() ||phone.length()>20) throw new ValidationException("Введён пустой номер телефона или его длина больше 20 символов");
        this.phone = phone;
    }
}
