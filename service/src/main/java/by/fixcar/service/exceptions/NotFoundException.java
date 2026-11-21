package by.fixcar.service.exceptions;

import by.fixcar.exceptions.AutoServiceException;


public class NotFoundException extends AutoServiceException {
    public NotFoundException(String entity,int id){
        super(entity + " с id: " + id + " не найдено");
    }
}
