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


    public static Status fromRus(String rusStatus){
        String[] rusWords = {"в процессе","выполнено","отменено"};
        return switch(rusStatus){
            case "в процессе" -> IN_PROGRESS;
            case "выполнено" -> COMPLETED;
            case "отменено" -> CANCELLED;
            default -> throw new ValidationException("Введен статус,котого нет!");
        };
    }
    public String getRusStatus(){
        return status;
    }

}
