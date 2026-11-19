package by.fixcar.exceptions;

public class DataAccessException extends AutoServiceException {
    public DataAccessException(String message,Throwable e){
        super(message,e);
    }
}
