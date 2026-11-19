package by.fixcar.exceptions;

public class AutoServiceException extends RuntimeException{

    public AutoServiceException(String message){
        super(message);
    }


    public AutoServiceException(String message, Throwable e){
        super(message,e);
    }
}
