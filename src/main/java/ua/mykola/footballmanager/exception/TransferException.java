package ua.mykola.footballmanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class TransferException extends RuntimeException {
    public TransferException(String message) {
        super(message);
    }
}
