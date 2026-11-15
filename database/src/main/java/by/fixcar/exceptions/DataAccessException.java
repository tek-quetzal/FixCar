package by.fixcar.exceptions;

public class DataAccessException extends RuntimeException {
    public DataAccessException(String message,Throwable e){
        super(message,e);
    }
}
