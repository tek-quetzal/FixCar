package by.fixcar.model;

import by.fixcar.exceptions.ValidationException;

public enum Status {
    IN_PROGRESS("в процессе"),
    COMPLETED("выполнено"),
    CANCELLED("отменено");

    private final String status;

    Status(String status){
        this.status = status;
    }


    public String getRuStatus(){
        return status;
    }
}