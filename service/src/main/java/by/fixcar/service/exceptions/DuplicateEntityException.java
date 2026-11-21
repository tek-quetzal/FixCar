package by.fixcar.service.exceptions;

import by.fixcar.exceptions.AutoServiceException;

public class DuplicateEntityException extends AutoServiceException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
